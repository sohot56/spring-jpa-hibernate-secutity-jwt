package com.example.demo.service.mapping;

public interface IMapping<T,K> {
	public K toDTO(T t);
	public T toEntity(K k);
}
