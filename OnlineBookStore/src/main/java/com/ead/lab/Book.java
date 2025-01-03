package com.ead.lab;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {
	public int id;
    public String title;
    public String author;
    public Long price;
}
