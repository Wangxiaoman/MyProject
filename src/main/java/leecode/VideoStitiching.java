package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class VideoStitiching {
	public static int videoStitching(int[][] clips, int time) {
		List<Clip> clipList = new ArrayList<>();
		for(int[] clip : clips) {
			Clip c = new Clip(clip[0],clip[1]);
			clipList.add(c);
		}
		return getClipBegin(clipList, 0, clipList.size(), time, 0);
    }
	
	public static int getClipBegin(List<Clip> clipList,int b,int e, int t, int counter) {
		Collections.sort(clipList);
		int mid = (clipList.size())/2;
		if(mid == 0) {
			return -1;
		}
		Clip m = clipList.get(mid);
		if (t >= m.getBegin() && t <= m.getEnd()) {
			if(m.getBegin() == 0) {
				return counter+1;
			} else {
				return getClipBegin(clipList, mid + 1, e, m.getBegin(), counter+1); 
			}
		} else if(t < m.getBegin()) {
			return getClipBegin(clipList, b, mid - 1, t, counter+1);
		} else {
			return getClipBegin(clipList, mid + 1, e, t, counter+1);
		}
	}
	
	public static void main(String[] args) {
		int[][] clips = new int[][] {{0,3},{2,5},{6,8}};
		System.out.println(videoStitching(clips, 0));
	}
}

class Clip implements Comparable<Clip>{
	private int begin;
	private int end;
	
	public Clip() {}
	public Clip(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}
	
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public int hashCode() {
		return Objects.hash(begin, end);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clip other = (Clip) obj;
		return begin == other.begin && end == other.end;
	}
	@Override
	public int compareTo(Clip o) {
		if(o.end == this.end) {
			if(o.begin > this.begin) {
				return 1;
			} else if(o.begin == this.begin) {
				return 0;
			} else {
				return -1;
			}
		} else if(o.end > this.end) {
			return 1;
		} else {
			return -1;
		}
	}
}
