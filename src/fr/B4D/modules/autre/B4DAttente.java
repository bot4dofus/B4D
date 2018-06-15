package fr.B4D.modules.autre;

public final class B4DAttente {

	  /***********/
	 /* ATTENTE */
	/***********/
	
	public static void Attendre(Double timeOut) {
		try {
			Thread.sleep((long) (timeOut*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
