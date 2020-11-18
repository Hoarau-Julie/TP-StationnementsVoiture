package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage 
         * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) 
            throws Exception {
                 // Et si la voiture est déjà dans un garage ? 
                 if (!this.estDansUnGarage()) 
                 {
                 Stationnement s = new Stationnement(this, g);
                 myStationnements.add(s);
                    } 
                 else {
                 //la voiture est dans un garage
            throw new Exception("La voiture est déjà dans un garage.");
        }
    }
	/**
	 * Fait sortir la voiture du garage 
         * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception
        {
          if (this.estDansUnGarage()) 
            {
            LinkedList<Stationnement> s;
            s = (LinkedList) myStationnements;
            // Trouver le dernier stationnement de la voiture
            // Terminer ce stationnement
            // la méthode pollLast permet de renvoyer et supprimer le dernier élément de la liste
            Stationnement sLast = s.pollLast(); // La voiture sort du garage
            sLast.terminer();
            s.addLast(sLast);
            }
          else 
            {
            //la voiture n'est pas dans un garage
            throw new Exception("La voiture n'est pas dans un garage.");
            }
        }

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisited() 
        {
        // Création d'une collection qui va contenir tous les garages visités
            Set<Garage> garagesVisited;
        garagesVisited = new HashSet<>();
        // On parcourt la liste des stationnements qui contient les garages visites
        this.myStationnements.forEach(s -> { garagesVisited.add(s.getGarage());});     // Ajouter tous les garages dans le set
        return garagesVisited;
        }
	

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
            if (!this.myStationnements.isEmpty()) 
                {
                        LinkedList<Stationnement> s;
                        s = (LinkedList) this.myStationnements;
                        Stationnement sLast = s.peekLast(); //Récupère,mais ne supprime pas le dernier élément de cette liste, ou renvoie null si cette liste est vide.
                        
                        // Vrai si le dernier stationnement est en cours
                        return sLast.estEnCours();
                 }
        return false;
        }

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des dates d'entrée / sortie dans ce
	 * garage
	 * <br>Exemple :
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) 
        {
            for (Garage g : this.garagesVisited()) 
            {
                out.println(g + ":");
                for (Stationnement s : this.myStationnements) 
                    {
                        if (g.equals(s.getGarage())) 
                        {
                            out.println(s);
                        }
            }
        }
	}

}
