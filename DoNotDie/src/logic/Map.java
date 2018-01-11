package logic;

import java.util.Random;

public class Map
{
	public Room current;
	private Room [] layout;
	
	
	// Default constructor creates randomized Map with 14 rooms, at level 5
	public Map ()
	{
		int roomnum = 14;
		int level = 5;
		int b;
		int l;
		int c;
		int r;
		int u;
		int d;
		int [][] Bcons = new int [2][7]; 
		int [][] Lcons = new int [2][7]; 
		int [][] Ucons = new int [2][7]; 
		Random roller = new Random ();
		
		layout = new Room [roomnum];
		
		resetArr (Bcons [0]);
		resetArr (Bcons [1]);
		resetArr (Lcons [0]);
		resetArr (Lcons [1]);
		resetArr (Ucons [0]);
		resetArr (Ucons [1]);
		
		// Create randomized Back/Center connections
		Bcons [0] = randomizeArr (roller, roomnum, Bcons [0], null);
		Bcons [1] = randomizeArr (roller, roomnum, Bcons [1], Bcons [0]);
		// Create randomized Left/Right connections
		Lcons [0] = randomizeArr (roller, roomnum, Lcons [0], null);
		Lcons [1] = randomizeArr (roller, roomnum, Lcons [1], Lcons [0]);
//		// Create randomized Up/Down connections
		Ucons [0] = randomizeArr (roller, roomnum, Ucons [0], null);
		Ucons [1] = randomizeArr (roller, roomnum, Ucons [1], Lcons [0]);
		
		// Create rooms with the randomized connections
		for (int i = 0; i < roomnum; i++)
		{
			b = inArr (i, Bcons [0]);
			l = inArr (i, Lcons [0]);
			c = inArr (i, Bcons [1]);
			r = inArr (i, Lcons [1]);
			u = inArr (i, Ucons [0]);
			d = inArr (i, Ucons [1]);
			
			if (b >= 0) b = (Bcons [1][b]);
			if (l >= 0) l = (Lcons [1][l]);
			if (c >= 0) c = (Bcons [0][c]);
			if (r >= 0) r = (Lcons [0][r]);
			if (u >= 0) u = (Ucons [1][u]);
			if (d >= 0) d = (Ucons [0][d]);
			
			layout [i] = new Room (roller.nextInt (level / 2), roller.nextInt (level), level, b, l, c, r, u, d, i);
		}
		
		current = layout [0];

	}
	
	// Procedural map generation. Pass in the number of rooms, and the level of the rooms
	public Map (int roomnum, int level)
	{
		int len = roomnum / 2;
		int b;
		int l;
		int c;
		int r;
		int u;
		int d;
		int [][] Bcons = new int [2][len];
		int [][] Lcons = new int [2][len];
		int [][] Ucons = new int [2][len];
		Random roller = new Random ();
		
		layout = new Room [roomnum];
		
		// Initialize the connection arrays
		resetArr (Bcons [0]);
		resetArr (Bcons [1]);
		resetArr (Lcons [0]);
		resetArr (Lcons [1]);
		resetArr (Ucons [0]);
		resetArr (Ucons [1]);
		
		// Create randomized Back/Center connections
		Bcons [0] = randomizeArr (roller, roomnum, Bcons [0], null);
		Bcons [1] = randomizeArr (roller, roomnum, Bcons [1], Bcons [0]);
		// Create randomized Left/Right connections
		Lcons [0] = randomizeArr (roller, roomnum, Lcons [0], null);
		Lcons [1] = randomizeArr (roller, roomnum, Lcons [1], Lcons [0]);
//		// Create randomized Up/Down connections
		Ucons [0] = randomizeArr (roller, roomnum, Ucons [0], null);
		Ucons [1] = randomizeArr (roller, roomnum, Ucons [1], Lcons [0]);
		
		// Create rooms with the randomized connections
		for (int i = 0; i < roomnum; i++)
		{
			b = inArr (i, Bcons [0]);
			l = inArr (i, Lcons [0]);
			c = inArr (i, Bcons [1]);
			r = inArr (i, Lcons [1]);
			u = inArr (i, Ucons [0]);
			d = inArr (i, Ucons [1]);
			
			if (b >= 0) b = (Bcons [1][b]);
			if (l >= 0) l = (Lcons [1][l]);
			if (c >= 0) c = (Bcons [0][c]);
			if (r >= 0) r = (Lcons [0][r]);
			if (u >= 0) u = (Ucons [1][u]);
			if (d >= 0) d = (Ucons [0][d]);
			
			layout [i] = new Room (roller.nextInt (level / 2), roller.nextInt (level), level, b, l, c, r, u, d, i);
		}
		
		current = layout [0];
	}
	
	public void printMap ()
	{
		for (int i = 0; i < layout.length; i++)
			System.out.println (layout [i].toString () + "\n");
	}
	
	// Movement methods. Change the current room to that of the specified direction, linked list style
	public void moveBack ()
	{
		if (current.connections [0] >= 0)
			current = layout [current.connections [0]];
	}
	public void moveLeft ()
	{
		if (current.connections [1] >= 0)
			current = layout [current.connections [1]];
	}
	public void moveCenter ()
	{
		if (current.connections [2] >= 0)
			current = layout [current.connections [2]];
	}
	public void moveRight ()
	{
		if (current.connections [3] >= 0)
			current = layout [current.connections [3]];
	}
	public void moveUp ()
	{
		if (current.connections [4] >= 0)
			current = layout [current.connections [4]];
	}
	public void moveDown ()
	{
		if (current.connections [5] >= 0)
			current = layout [current.connections [5]];
	}

	// Checks if all rooms have been cleared. Returns true if so, false if not
	public Boolean allCleared ()
	{
		for (int i = 0; i < layout.length; i++)
			if (!layout [i].roomCleared ())
				return false;
		return true;
	}
	
	// Checks if a number is present in an array
	// Pass in the number to look for, and the array to check
	// Returns the index of the number or -1 if not present
	private int inArr (int con, int [] cons)
	{
		if (cons != null)
			for (int i = 0; i < cons.length; i++)
				if (cons [i] == con)
					return i;
		
		return -1;
	}
	// Replaces the contents of the passed in array with zeros
	private void resetArr (int [] arr)
	{
		for (int i = 0; i < arr.length; i++)
			arr [i] = -1;
	}
	// Fill an array with random ints, ensuring no duplicates
	// Pass in a Random object, the number of rooms, an array initialized with -1's, and an array of the
	//    previously used connections, or null if this is the 0 index of the connections double array
	// Returns the passed in array, filled with non-negative numbers
	private int [] randomizeArr (Random r, int roomnum, int [] arr, int [] two)
	{
		int temp;
		int poollen = 0;
		int [] pool = new int [roomnum];
		
		// Initialize the pool of connections to draw from, excluding all connections used in array two
		resetArr (pool);
		for (int i = 0; i < roomnum; i++)
		{
			if (inArr (i, two) < 0)
			{
				pool [inArr (-1, pool)] = i;
				poollen++;
			}
		}
		
		// Randomly draw from pool of available connections to assign to array arr
		for (int i = 0; i < roomnum / 2; i++)
		{
			temp = r.nextInt (poollen);
			
			arr [i] = pool [temp];
			pool [temp] = -1;
			shift (pool, temp);
			poollen--;
		}
		
		return arr;
	}
	
	// Shifts the contents of an array down to be continuous from 0
	// Pass in the array to be shifted, and the starting index
	public void shift (int [] arr, int ind)
	{
		int hold;
		
		for (int i = ind; i < arr.length - 1 && arr [i + 1] != -1; i++)
		{
			hold = arr [i];
			arr [i] = arr [i + 1];
			arr [i + 1] = hold;
		}
	}
}