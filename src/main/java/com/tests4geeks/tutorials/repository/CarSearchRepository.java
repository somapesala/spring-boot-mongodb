package com.tests4geeks.tutorials.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.tests4geeks.tutorials.model.Car;

@Repository
public class CarSearchRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public Collection<Car> searchCars(String text) {
		
		
		// for searching/get all already available cars details
		 List<Car> findAll = mongoTemplate.findAll(Car.class);
		 for (Car car : findAll) {
			System.out.println("car details is :"+car);
		 }
		 
		 //get based on only one column record
		 List<Car> find = mongoTemplate.find(Query.query(Criteria.where("make").regex("soma")), Car.class);
		 System.out.println("the searching make details is size is :"+find.size());
		 for (Car car : find) {
				System.out.println("car details is :"+car);
			 }
		
		// get car details based on with given description/make/model data and returns it.
		return mongoTemplate.find(Query.query(new Criteria()
						.orOperator(Criteria.where("description").regex(text, "i"), 
									Criteria.where("make").regex(text, "i"), 
									Criteria.where("model").regex(text, "i"))
						), Car.class);
	}
	
}
