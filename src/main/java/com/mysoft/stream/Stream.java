package com.mysoft.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream {
	private enum Status {
		OPEN, CLOSED
	};

	private static final class Task {
		private final Status status;
		private final Integer points;

		Task( final Status status, final Integer points ) {
			this.status = status;
			this.points = points;
		}

		public Integer getPoints() {
			return points;
		}

		public Status getStatus() {
			return status;
		}

		@Override
		public String toString() {
			return String.format( "[%s, %d]", status, points );
		}
	}


	public static void main(String[] args) {
		final Collection< Task > tasks = Arrays.asList(
				new Task( Status.OPEN, 5 ),
				new Task( Status.OPEN, 13 ),
				new Task( Status.CLOSED, 8 ) 
				);


		//所有状态为OPEN的任务一共有多少分数
		final long totalPointsOfOpenTasks = tasks
				.stream()
				.filter( task -> task.getStatus() == Status.OPEN )
				.mapToInt( Task::getPoints )
				.sum();

		System.out.println( "Total points: " + totalPointsOfOpenTasks );

		final double totalPoints = tasks
				.stream()
				.parallel()
				.map( task -> task.getPoints() ) // or map( Task::getPoints ) 
				.reduce( 0, Integer::sum );

		System.out.println( "Total points (all tasks): " + totalPoints );


		//按照某种准则来对集合中的元素进行分组。
		final Map< Status, List< Task > > map = tasks
				.stream()
				.collect( Collectors.groupingBy( Task::getStatus ) );
		System.out.println( map );

		//计算整个集合中每个task分数（或权重）的平均值来结束task
		final Collection< String > result = tasks
				.stream()                                        // Stream< String >
				.mapToInt( Task::getPoints )                     // IntStream
				.asLongStream()                                  // LongStream
				.mapToDouble( points -> points / totalPoints )   // DoubleStream
				.boxed()                                         // Stream< Double >
				.mapToLong( weigth -> ( long )( weigth * 100 ) ) // LongStream
				.mapToObj( percentage -> percentage + "%" )      // Stream< String> 
				.collect( Collectors.toList() );                 // List< String > 

		System.out.println( result );

	}
}
