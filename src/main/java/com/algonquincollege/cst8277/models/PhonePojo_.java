package com.algonquincollege.cst8277.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-03-31T10:36:17.804-0400")
@StaticMetamodel(PhonePojo.class)
public class PhonePojo_ extends PojoBase_ {
	public static volatile SingularAttribute<PhonePojo, String> areaCode;
	public static volatile SingularAttribute<PhonePojo, String> phoneNumber;
	public static volatile SingularAttribute<PhonePojo, String> phoneType;
	public static volatile SingularAttribute<PhonePojo, EmployeePojo> owningEmployee;
}
