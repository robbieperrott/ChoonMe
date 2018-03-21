# Networking
## CSC3002F Networking Assignment
For this assignment we are required to design and implement a client-server based chat application that makes use of TCP sockets.
### Protocol Design and Specification
Pattern of communication needs to be client-server-based. 
Which means a server is responsible for the overall control and coordination of communication between parties.
Messages in text format consists of readable character strings, or binary formats. 
We are required to use text-based messaging.

Three types of messages can be defined: Commands, data transfer and control. 
* Command messages define the different stages of communication between parties - such as initiation and termination. 
* Data transfer mesages are used to carry the data that is exchanged between parties.
* Control messages manage the dialogue between parties.

Message structure constitutes of a header and a body. 
A header may contain fields that describe the actual data in the message. Such as: the message type, the command, recipient information and sequence information. The body is the message.

There must be communication rules that specify the sequence of messages at every stage of communication. 
Requires clearly specifying messages and reactions for every communication scenario.