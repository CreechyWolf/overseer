# overseer
A Java application that sends a HTTP GET request to the REST API for Discord to get the latest messages for a given channel and then sends a HTTP POST request to create a given message on the same channel via a chat bot if the latest messages contained a given command. The command, channel ID, and message to send are all configurable via program arguments.
