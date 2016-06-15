package com.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class JavaClient {
	public static void main(String[] args) {
		try {
			TTransport transport = new TSocket("121.201.63.168", 9090);
			TProtocol protocol = new TBinaryProtocol(transport);
			Task.Client client = new Task.Client(protocol);
			transport.open();
			Auth auth = new Auth("simmer", Type.GET_ANSWER);
			System.out.println(client.getTaskInfo(auth));
			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}
}
