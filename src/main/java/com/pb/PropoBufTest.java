package com.pb;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.pb.Person.Task.Answer;

public class PropoBufTest {
	public static void main(String[] args) throws Exception {
		try {
			File file = new File("/Users/xiaoman/workspace/MyProject/src/main/java/com/pb/145691935645609.data");
			InputStream fis = new FileInputStream(file);
			byte[] byt = new byte[fis.available()];
			fis.read(byt);
			Person.Task task = Person.Task.parseFrom(byt);
			List<Person.Task.Answer> list = task.getAnswerList();
			int sum = 0;
			for(Answer answer : list){
				sum += answer.getData();
			}
			System.out.println("sum:"+sum);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}

	}
}
