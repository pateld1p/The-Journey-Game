package com.mycompany.a3;

import java.util.ArrayList;

public interface ICollider {

	public boolean collidesWith(ICollider otherObject);
	public void handleCollision(ICollider otherObject, GameWorld gw);

}
