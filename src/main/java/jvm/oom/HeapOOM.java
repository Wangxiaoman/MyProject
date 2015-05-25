package jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args：-Xms40m -Xmx40m -XX:+HeapDumpOnOutOfMemoryError
 * 20m太小了，本机上配置之后，无法运行
 * 
 * @author zzm
 */
public class HeapOOM {

	static class OOMObject {

	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while (true) {
			list.add(new OOMObject());
		}
	}
}
