package com.jisasoftech.ADVNIC.utils;

import com.jcraft.jsch.*;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoteOTPFetcher {
	public static String fetchOTPFromServer() {
		String host = ConfigReader.getProperty("server.ip");  
	    String user = ConfigReader.getProperty("server.user");  
	    String password = ConfigReader.getProperty("server.password");  
	    String command = "cat " + ConfigReader.getProperty("server.logfile");

        try {
            // Create an SSH session
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // Execute command on the remote server
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            channel.setInputStream(null);
            channel.setErrStream(System.err);

            // Read command output (log file content)
            InputStream in = channel.getInputStream();
            channel.connect();

            // Convert InputStream to String
            StringBuilder outputBuffer = new StringBuilder();
            int readByte;
            while ((readByte = in.read()) != -1) {
                outputBuffer.append((char) readByte);
            }

            // Close connections
            channel.disconnect();
            session.disconnect();

            // Extract OTP using regex
            return extractOTP(outputBuffer.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String extractOTP(String logData) {
        Pattern pattern = Pattern.compile("The OTP to change your password .* is (\\d{6})");
        Matcher matcher = pattern.matcher(logData);
        if (matcher.find()) {
            return matcher.group(1); // Extract OTP
        }
        return null;
    }
}
