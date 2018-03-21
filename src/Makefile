#MCKANG009, JSTJOR001 and PRRROB006
#A Simple makefile for our project
#defining the java compiler flag
JCC = javac

#Define a makefile variable for compilation flags
#the -g flag compiles with debugging information
JFLAGS = -g

#typing 'make' will invoke the first target entry in the makefile

default: ChatServer.class ChatClient.class ReadThread.class UserThread.class WriteThread.class

#This lays out the parameters to build a class, lays out the dependency between .class and .java
#Could be streamlined by using other variables and a loop, but 
ChatServer.class: ChatServer.java
	$(JCC) $(JFLAGS) ChatServer.java
ChatClient.class: ChatClient.java
	$(JCC) $(JFLAGS) ChatClient.java
ReadThread.class: ReadThread.java
	$(JCC) $(JFLAGS) ReadThread.java
UserThread.class: UserThread.java
	$(JCC) $(JFLAGS) UserThread.java
WriteThread.class: WriteThread.java
	$(JCC) $(JFLAGS) WriteThread.java	

#To clean the director
clean:
	$(RM) *.class