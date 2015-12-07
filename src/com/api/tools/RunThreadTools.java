package com.api.tools;

public class RunThreadTools implements Runnable{

	String[] phones = {"13681109674", "13581525298", "13581651017", "13811519833", "13810776570"};
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		for(int i = 0; i < 3; i++){
//			String result = SendMessageTools.sendMessageForHX(RandomNoTools.getRandomArrayValue(phones), 1);
//			System.out.println(result + i + Thread.currentThread().getName());
//		}
	}
	
	public static void main(String[] args){
		RunThreadTools rt = new RunThreadTools();
		new Thread(rt, "线程1").start();
		new Thread(rt, "线程2").start();
		new Thread(rt, "线程3").start();
		new Thread(rt, "线程4").start();
	}

}
