package com.mycompany.a2;

public interface ICollection {
	public abstract void add(Object newObject);
	public abstract IIterator getIterator();
}