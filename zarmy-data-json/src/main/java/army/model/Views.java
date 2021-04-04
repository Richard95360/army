package army.model;

public class Views {
	
	public static class Common {}
	
	public static class Arme extends Common{}
	public static class ArmeAvecPhoto extends Arme{}
	
	public static class Commande extends Common{}
	public static class CommandeAvecLigneCommande extends Commande{}
	
	public static class LigneCommande extends Common{}
	
	public static class Civil extends Common{}
	
	public static class Guerrier extends Common{}
	public static class GuerrierAvecPhoto extends Guerrier{}
	
	public static class Role extends Common {}
	
	public static class User extends Common {}
	
	public static class Medecin extends Common{}
	public static class Pacifiste extends Common{}
	public static class CorqsMilitaire extends Common{}

	}
