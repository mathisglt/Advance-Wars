package main;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import main.Terrains.*;
import ressources.Affichage;
import ressources.Chemins;

public class Carte {
	protected String[][] carteString; // Double tableau de String utilisé pour stocker les cases lors du parsing de la
										// map.
	protected Coordonnees[][] carteStringCoord; // Double tableau de type Coordonnees
	protected List<Coordonnees> carteUniteCoord; // Liste de toutes les coordonnees où se trouve une unité après //
													// création de la map.
	protected List<Unite> listUnite; // Liste des Unites présentes sur le terrain.
	protected List<ProprieteSpeciale> listPropriete; // Liste des Unites présentes sur le terrain.
	public boolean debut = true;

	Font police = new Font("Sans_Serif", Font.BOLD, 20);
	Color grisnoir = new Color(50, 50, 50);

	/**
	 * Constructeur du type Carte
	 * Initalise une carte avec 2 ArrayList : (carteUniteCoord et listUnite)
	 * 
	 * @param none
	 */
	public Carte() {
		super();
		this.carteUniteCoord = new ArrayList<Coordonnees>();
		;
		this.listUnite = new ArrayList<Unite>();
		this.listPropriete = new ArrayList<ProprieteSpeciale>();
		;
	}

	/**
	 * Fonction permettant d'afficher la carte précedemment chargé en format
	 * .awdcmap.
	 * Fonction qui initialise également les Listes primordiales au bon
	 * fonctionnement du jeu :
	 * carteStringCoord, carteUniteCoord.
	 * carteUniteCoord : Liste de Unite (int x, int y , numéro de joueur)
	 * carteStringCoord: Liste de Coordonnes (int x, int y , Terrain, numéro de
	 * joueur)
	 * 
	 */
	public void chargementCarte() {

		// Parcourir le tableau
		for (int i = 0; i < this.carteString.length; i++) {
			for (int j = 0; j < this.carteString[0].length; j++) {
				// Si il existe une unite sur le terrain
				if (this.carteString[i][j].contains(";")) {
					// On sépare les informations en 3 blocs distincts
					String[] splitter1 = this.carteString[i][j].split(";"); // {{Terrain} , {Unite , 1}}
					String[] splitter2 = splitter1[1].split(":"); // {Unite , 1}}

					// Si l'unite est sur un Terrain on crée une Coordoonnes avec ce terrain et sa
					// couleur, si c'est un batiment spécial on y ajoute le numéro du joueur
					// Et nous l'ajoutons ensuite au tableau carteStringCoord

					switch (splitter1[0]) {
						case "Ville":
							this.carteStringCoord[i][j] = new Coordonnees(i, j, "Ville",
									Integer.parseInt(splitter2[1]));
							indentProp(splitter2[1]);
							this.listPropriete.add(new Ville(this.carteStringCoord[i][j].terrainImage,
									this.carteStringCoord[i][j].getProp(), this.carteStringCoord[i][j].getX(),
									this.carteStringCoord[i][j].getY()));
							break;
						case "QG":
							this.carteStringCoord[i][j] = new Coordonnees(i, j, "QG", Integer.parseInt(splitter2[1]));
							this.listPropriete.add(new QG(this.carteStringCoord[i][j].terrainImage,
									this.carteStringCoord[i][j].getProp(), this.carteStringCoord[i][j].getX(),
									this.carteStringCoord[i][j].getY()));
							indentProp(splitter2[1]);
							break;
						case "Usine":
							this.carteStringCoord[i][j] = new Coordonnees(i, j, "Usine",
									Integer.parseInt(splitter2[1]));
							this.listPropriete.add(new Usine(this.carteStringCoord[i][j].terrainImage,
									this.carteStringCoord[i][j].getProp(), this.carteStringCoord[i][j].getX(),
									this.carteStringCoord[i][j].getY()));
							indentProp(splitter2[1]);
							break;
						default:
							this.carteStringCoord[i][j] = new Coordonnees(i, j, splitter1[0]);
					}
					// Ajouter les coordonnées de l'unite dans la liste carteUniteCoord
					this.carteUniteCoord.add(new Coordonnees(i, j, Integer.parseInt(splitter2[1]), splitter2[0]));
				}

				// L'élément à charger/afficher est seulement une Propriété avec une couleur
				// spécifique
				else if (this.carteString[i][j].contains(":")) {
					// On sépare les informations en 2 blocs distincts
					// splitter[0] = Terrain et splitter[1] = Numéro de Joueur
					String[] splitter = this.carteString[i][j].split(":");
					switch (splitter[0]) {
						// Si l'unite est sur un Terrain on crée une Coordoonnes avec ce terrain et sa
						// couleur, si c'est un batiment spécial on y ajoute le numéro du joueur
						// Et nous l'ajoutons ensuite au tableau carteStringCoord

						case "Ville":
							this.carteStringCoord[i][j] = new Coordonnees(i, j, "Ville", Integer.parseInt(splitter[1]));
							this.listPropriete.add(new Ville(this.carteStringCoord[i][j].terrainImage,
									this.carteStringCoord[i][j].getProp(), this.carteStringCoord[i][j].getX(),
									this.carteStringCoord[i][j].getY()));
							indentProp(splitter[1]); // Ajoute
							break;
						case "QG":
							this.carteStringCoord[i][j] = new Coordonnees(i, j, "QG", Integer.parseInt(splitter[1]));
							this.listPropriete.add(new QG(this.carteStringCoord[i][j].terrainImage,
									this.carteStringCoord[i][j].getProp(), this.carteStringCoord[i][j].getX(),
									this.carteStringCoord[i][j].getY()));
							indentProp(splitter[1]);
							break;
						case "Usine":
							this.carteStringCoord[i][j] = new Coordonnees(i, j, "Usine", Integer.parseInt(splitter[1]));
							this.listPropriete.add(new Usine(this.carteStringCoord[i][j].terrainImage,
									this.carteStringCoord[i][j].getProp(), this.carteStringCoord[i][j].getX(),
									this.carteStringCoord[i][j].getY()));
							indentProp(splitter[1]);
							break;
						default:
							this.carteStringCoord[i][j] = new Coordonnees(i, j, this.carteString[i][j]);
					}
				} else {
					this.carteStringCoord[i][j] = new Coordonnees(i, j, this.carteString[i][j]);
				}
			}
		}
		afficheCarte();
		for (int w = 0; w < this.carteUniteCoord.size(); w++) {
			// Afficher les Unites en fonction de leur position , leur camp et leur
			// disponibilité
			Affichage.dessineImageDansCase(this.carteUniteCoord.get(w).x, this.carteUniteCoord.get(w).y, Chemins
					.getCheminUnite(this.carteUniteCoord.get(w).prop, true, this.carteUniteCoord.get(w).uniteImage));
			Affichage.afficheTexteDansCase(this.carteUniteCoord.get(w).x, this.carteUniteCoord.get(w).y, "10",
					Color.white,
					0.8, 0.2, police);
		}
		System.out.println("chargement fini");
	}

	/**
	 * Fonction augmentant de 1 le nbMaison en fonction de son numéro
	 * 
	 * @param int Numéro de Joueur
	 * @return none
	 */
	public void indentProp(String numero) {
		if (numero.contains("1")) {
			Jeu.J1.setNbProprietes(Jeu.J1.getNbProprietes() + 1);
		} else if (numero.contains("2")) {
			Jeu.J2.setNbProprietes(Jeu.J2.getNbProprietes() + 1);
		}
	}

	/**
	 * Fonction permettant d'afficher la carte à l'écran, prenant en compte
	 * Les Unités et leurs paramètres, les propriété et les décors
	 * 
	 * @param none
	 * @return none
	 */
	public void afficheCarte() {
		for (int i = 0; i < this.carteString.length; i++) {
			for (int j = 0; j < this.carteString[0].length; j++) {
				// Si le terrain est basique (Foret , Plaine , Eau, Montagne) , l'afficher
				// Si c'est une propriété , l'affiché en fonction de sa propriété ( rouge, bleu
				// , neutre)
				switch (this.carteStringCoord[i][j].terrain.getClass().toString()) {
					case "class main.Terrains.Foret":
					case "class main.Terrains.Plaine":
					case "class main.Terrains.Eau":
					case "class main.Terrains.Montagne":
						Affichage.dessineImageDansCase(this.carteStringCoord[i][j].x, this.carteStringCoord[i][j].y,
								Chemins.getCheminTerrain(this.carteStringCoord[i][j].terrainImage));
						break;
				}
			}
		}
		// Affichage des batiments en fonction de leurs couleurs et positions
		for (int i = 0; i < this.listPropriete.size(); i++) {
			Affichage.dessineImageDansCase(this.listPropriete.get(i).getX(), this.listPropriete.get(i).getY(),
					Chemins.getCheminPropriete(this.listPropriete.get(i).getTerrainImage(),
							this.listPropriete.get(i).getProp()));
			Affichage.afficheTexteDansCase(this.listPropriete.get(i).getX(), this.listPropriete.get(i).getY(),
					"" + this.listPropriete.get(i).getResistance(), grisnoir, 0.5, -0.2, police);
		}
		for (int t = 0; t < this.listUnite.size(); t++) {
			// Affichage des Unités
			if (this.carteUniteCoord.get(t).x == this.listUnite.get(t).getPosition().getX()
					&& this.carteUniteCoord.get(t).y == this.listUnite.get(t).getPosition().getY()) {
				// Affichage de l'unite en fonction de sa position , sa disponibilité et son
				// équipe
				Affichage.dessineImageDansCase(this.listUnite.get(t).getPosition().getX(),
						this.listUnite.get(t).getPosition().getY(),
						Chemins.getCheminUnite(this.listUnite.get(t).getPosition().getProp(),
								this.listUnite.get(t).getType().isDispo(), this.listUnite.get(t).getType().getImage()));
				// Variable police et couleur nécessaire à l'affichage en Temps Réel des points
				// de vie de l'unité
				int hp = (int) this.listUnite.get(t).getHP();
				// Affichage des points de vie en blanc en bas à droite de la case.
				Affichage.afficheTexteDansCase(this.listUnite.get(t).getPosition().getX(),
						this.listUnite.get(t).getPosition().getY(), "" + hp, Color.white, 0.8, 0.2, police);
			}
		}
		// Vérification des Captures en cours , reset des resistances à 20 si aucun
		// Joueur ennemi n'est sur la propriété
		for (int i = 0; i < listPropriete.size(); i++) {
			listPropriete.get(i).verifCaptureProcessing();
		}
	}

	/**
	 * Fonction affichant au dessus de la map , le contenu demandé
	 * 
	 * @param none
	 */
	public void afficheStatutJeu() {
		// Remet à zéro l'affichage
		Affichage.videZoneTexte();
		switch (Jeu.getIndexJoueurActif()) {
			// Affiche le joueur qui doit jouer en fonction de sa couleur
			case 1:
				Affichage.afficheTexteDescriptif("C'est au Joueur Rouge de jouer | Argent : " + Jeu.J1.getArgent());
				break;
			case 2:
				Affichage.afficheTexteDescriptif("C'est au Joueur Bleu de jouer | Argent : " + Jeu.J2.getArgent());
		}
	}

	/**
	 * Fonction créeant les Unites , si la map chargé en contenait
	 * 
	 * @param none
	 */
	public void verifListUnite() {
		// Cas où une map est généré avec déjà des unites sur le plateau , il faut alors
		// en plus de les afficher
		// Créer ces Unites et les ajouter au tableau regroupant toutes les unites
		// actuelles
		if (this.listUnite.size() == 0 && this.carteUniteCoord.size() != 0) {
			for (int b = 0; b < this.carteUniteCoord.size(); b++) {
				this.listUnite.add(Unite.trouverAjouterUnite(this.carteUniteCoord.get(b),
						this.carteUniteCoord.get(b).nameunite, this.carteUniteCoord.get(b).prop));
			}
		}
	}

	// Getters/Setters des attributs nécessaires dans les fonctions/classes
	// auxiliaires
	public Coordonnees[][] getCarteStringCoord() {
		return carteStringCoord;
	}

	public List<Coordonnees> getCarteUniteCoord() {
		return carteUniteCoord;
	}

	public List<Unite> getListUnite() {
		return listUnite;
	}

	public void setCarteStringCoord(Coordonnees[][] carteStringCoord) {
		this.carteStringCoord = carteStringCoord;
	}

	public void setCarteUniteCoord(List<Coordonnees> carteUniteCoord) {
		this.carteUniteCoord = carteUniteCoord;
	}

	public void setListUnite(List<Unite> listUnite) {
		this.listUnite = listUnite;
	}

	public List<ProprieteSpeciale> getListPropriete() {
		return listPropriete;
	}

	public void setListPropriete(List<ProprieteSpeciale> listProriete) {
		this.listPropriete = listProriete;
	}

}
