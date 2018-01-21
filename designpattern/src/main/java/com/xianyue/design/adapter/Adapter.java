package com.xianyue.design.adapter;

import lombok.Setter;

public class Adapter implements Target {

  @Setter private Adaptee adaptee;

  @Override
  public void twoPoint() {
    // do some convert
    adaptee.ThreePoint();
    System.out.println("converter");
    System.out.println("two point");
  }
}
