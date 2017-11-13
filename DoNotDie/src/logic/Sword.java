package logic;

public class Sword extends Weapon
{
	public Sword (String type)
	{
		System.out.println (type);
		
		if (type.equals ("Iron"))
		{
			name = "Iron Sword";
			value = 10;
			damage = 8;
			weight = 8;
		}
		else if (type.equals ("Steel"))
		{
			name = "Steel Sword";
			value = 15;
			damage = 12;
			weight = 10;
		}
		else if (type.equals ("Elven"))
		{
			name = "Elven Sword";
			value = 20;
			damage = 15;
			weight = 10;
		}
		else if (type.equals ("Dwarvish"))
		{
			name = "Dwarvish Sword";
			value = 25;
			damage = 18;
			weight = 13;
		}
		else if (type.equals ("Glass"))
		{
			name = "Glass Sword";
			value = 30;
			damage = 21;
			weight = 14;
		}
		else if (type.equals ("Orcish"))
		{
			name = "Orcish Sword";
			value = 35;
			damage = 23;
			weight = 16;
		}
		else if (type.equals ("Ancient"))
		{
			name = "Ancient Sword";
			value = 50;
			damage = 27;
			weight = 20;
		}
		else if (type.equals ("Demonic"))
		{
			name = "Demonic Sword";
			value = 60;
			damage = 34;
			weight = 25;
		}
	}
	
	public String toString ()
	{
		return name + "\nValue: " + value + "\nDamage: " + damage + "\nWeight: " + weight;
	}
	
	public Boolean equals (Sword other)
	{
		return name.equals (other.getName ());
	}
	
	public int getDamage () { return damage; }
	public int getValue () { return value; }
	public int getWeight () { return weight; }
	public String getName () { return name; }
	
	private int value = 0, damage = 0, weight = 0;
	private String name = "";
}