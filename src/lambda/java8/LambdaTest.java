package lambda.java8;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.Test;

public class LambdaTest{

	@Test
	public void test(){
		System.out.println("=== RunnableTest ===");
		Runnable r1 = new Runnable(){
			@Override
			public void run(){
				System.out.println("Hello world one!");
			}
		};
		// Lambda Runnable
		Runnable r2 = () -> System.out.println("Hello world two!");
		r1.run();
		r2.run();
	}
	
	@Test
	public void Test1(){
		Person person1 = new Person("Tom",16);
		Person person2 = new Person("Mary",17);
		Person person3 = new Person("Peter",18);
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(person3);
		list.add(person2);
		list.add(person1);
		System.out.println("List is: "+list);
		
		Collections.sort(list, new Comparator<Person>(){
			public int compare(Person p1, Person p2){
				return p1.getName().compareTo(p2.getName());
			}
		});
		System.out.println("After sort list by name is: "+list);
		//Lambda
		Collections.sort(list, (p1, p2) -> p1.getAge()>p2.getAge()?-1:p1.getAge()==p2.getAge()?0:1);
		System.out.println("After sort list by age is: "+list);

	}
	
	@Test
	public void Test3(){
		JButton testButton = new JButton("Test Button");
		testButton.addActionListener(e -> System.out.println("Click Detected by Lambda Listner"));
		JFrame frame = new JFrame("Listener Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(testButton, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}

class Person{
	private String name;
	private int age;
	
	public Person(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getAge(){
		return age;
	}
	public void setAge(int age){
		this.age = age;
	}
	
	@Override
	public String toString(){
		return name+","+age;
	}
}
