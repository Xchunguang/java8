package com.mysoft.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collector {


	/**
	 * 简单收集，收集某属性集合
	 */
	public Set<String> collectDate(List<UserVO> userList){
		return userList.stream().map(UserVO::getUserName).collect(Collectors.toSet());
	}

	/**
	 * 简单过滤,查找一个
	 * @param userList
	 * @return
	 */
	public UserVO simpleFilter(List<UserVO> userList,String userName){
		return userList.stream().filter((userVO)->userVO.getUserName().equals(userName)).findFirst().orElse(null);
	}

	/**
	 * 简单过滤查找列表
	 * @param userList
	 * @param age
	 * @return
	 */
	public List<UserVO> simpleFilterFindList(List<UserVO> userList,int age){
		return userList.stream().filter((userVO)->userVO.getAge()==age).collect(Collectors.toList());
	}

	/**
	 * 检查是否所有元素都匹配条件
	 * @param userList
	 * @return
	 */
	public boolean matchAllUser(List<UserVO> userList){
		return userList.stream().allMatch((userVO)->userVO.getAge()>=0);
	}

	/**
	 * 将集合分组
	 * 根据年龄分组
	 * @param userList
	 * @return
	 */
	public Map<Integer,List<UserVO>> groupUserVO(List<UserVO> userList){
		return userList.stream().collect(Collectors.groupingBy(userVO -> userVO.getAge()));
	}

	/**
	 * 将数据收集成一个映射
	 * Collectors.toMap(键，值),此方法存在问题，当键相同时会抛出异常，改进方法如下
	 * @param userList
	 * @return
	 */
	public Map<String,UserVO> toUserMap(List<UserVO> userList){
		return userList.stream().collect(Collectors.toMap(UserVO::getUserName, userVO -> userVO));
	}

	/**
	 * 将数据收集为一个映射，同时如果出现键重复的问题，取重复的第一个数据
	 * @param userList
	 * @return
	 */
	public Map<String,UserVO> toUserMapRepeat(List<UserVO> userList){
		return userList.stream().collect(Collectors.toMap(UserVO::getUserName,userVO -> userVO,(t1,t2) -> t1));
	}


	/**
	 * 将数据收集为一个映射，同时解决重复问题，同时选择生成的Map类为LinkedHashMap
	 * @param userList
	 * @return
	 */
	public Map<String,UserVO> toUserLinkMap(List<UserVO> userList){
		return userList.stream().collect(Collectors.toMap(UserVO::getUserName,userVO -> userVO,(t1,t2) -> t1 ,LinkedHashMap::new));
	}

	/**
	 * 从集合中查找相应条件的数据：查找年龄最大的user
	 * @param userList
	 * @return
	 */
	public UserVO findMaxAgeUser(List<UserVO> userList){
		return userList.stream().collect(Collectors.collectingAndThen(Collectors.maxBy((t1,t2) -> t1.getAge() - t2.getAge()), Optional::get));
	}

	/**
	 * 统计年龄总数
	 * @param userList
	 * @return
	 */
	public int totalAge(List<UserVO> userList){
		return userList.stream().collect(Collectors.summingInt(UserVO::getAge));
	}

	/**
	 * 合并用户名
	 * @param userList
	 * @return
	 */
	public String mergeUserName(List<UserVO> userList){
		return userList.stream().map(UserVO::getUserName).collect(Collectors.joining("/"));
	}

	/**
	 * 分割数据集，用年龄分割
	 * @param userList
	 * @return
	 */
	public Map<Boolean,List<UserVO>> splitUserList(List<UserVO> userList){
		return userList.stream().collect(Collectors.partitioningBy(userVO -> userVO.getAge()>15));
	}

	/**
	 * 对数值类型生成统计信息
	 * @param userList
	 */
	public void getCalculation(List<UserVO> userList){
		IntSummaryStatistics summaryStatistics = userList.stream().map(UserVO::getAge).collect(Collectors.summarizingInt(Integer::intValue));
		System.out.println(summaryStatistics.getAverage()); 
		System.out.println(summaryStatistics.getCount()); 
		System.out.println(summaryStatistics.getMax()); 
		System.out.println(summaryStatistics.getMin()); 
		System.out.println(summaryStatistics.getSum()); 
		//统计信息同样可以与另一个统计信息组合起来：
		summaryStatistics.combine(summaryStatistics);
	}

	/**
	 * limit 是取前n个元素
	 * skip是丢弃前n个元素
	 * @param userList
	 * @return
	 */
	public List<UserVO> limitAndSkip(List<UserVO> userList){
		return userList.stream().limit(10).skip(2).collect(Collectors.toList());
	}


	/**
	 * 排序集合
	 * @param userList
	 * @return
	 */
	public List<UserVO> sortList(List<UserVO> userList){
		return userList.stream().sorted((t1,t2) -> t1.getUserName().compareTo(t2.getUserName())).collect(Collectors.toList());
	}

	/**
	 * 找到最长一行的长度
	 * @throws IOException
	 */
	public void findMaxLine() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("c:\\SUService.log"));
		int longest = br.lines().
				mapToInt(String::length).
				max().
				getAsInt();
		br.close();
		System.out.println(longest);
	}

	
	/**
	 * 自定义流
	 */
	public void generateStream(){
		Random ran = new Random();
		Supplier<Integer> stream = ran::nextInt;
		Stream.generate(stream).limit(10).forEach((value) -> System.out.println(value));
	}

	public static void main(String[] args) {
		List<UserVO> userList = new ArrayList<>();
		userList.add(new UserVO("xiaoming",14));
		userList.add(new UserVO("xiaogong",15));
		userList.add(new UserVO("xiaogang",16));
		userList.add(new UserVO("xiaolan",17));
		Collector collector = new Collector();
		Map<Boolean,List<UserVO>> map = new HashMap<>();
		map = collector.splitUserList(userList);

		collector.getCalculation(userList);
		collector.generateStream();
	}


}
class UserVO{
	String userName;
	Integer age;

	public UserVO(String userName, Integer age) {
		super();
		this.userName = userName;
		this.age = age;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
