// Fichier d'inclusion, Déclaration des constantes et Déclaration des varaiables globales

                       

//Configuration initiale: Déclaration des variables locales, Configuration des broches, Initialisation des variables, Initialisation des interruptions

void setup() {

  Serial.begin(9600);   // Vitesse de communication du port série Natif

  Serial.println("Port Serie Natif");     
  
}
// Fonction principale: Instructions exécutées en boucle

void loop() {


  if ( Serial.available() ) {        
    int lu_ASCII = Serial.read();   
                                     
    Serial.println(lu_ASCII);           // Affichage sur le terminal via TX de Arduino                                        
    Serial.println((char)lu_ASCII);     // Affichage sur le terminal                                 

    if(lu_ASCII==97)   // Si le caractère reçu sur la broche Native Rx = 97 (correspondant au caractère "a") ;
    {
     Serial.write(lu_ASCII);    //Affichage sur le terminal connecté au TX Natif;
    }
  else {
       }
  
  } 
  
  else {
    Serial.println("Rien recu sur le port Natif RX");                     
  }
 
  delay(2000);

}
