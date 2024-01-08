# bceao-cursus

Application accédée par les établisements bancaires et le gestionnaire de compensation

API établissement :
- Soumission des transactions par le créditeur
- Confirmation des transactions par le débiteur
- Récupération des transactions confirmées, en attente de compensation, affichage du solde
- Historique des paiements

API Gestionnaire
- Edition des règles de compensation (limite de montant, limite de temps pour la confirmation, horaire d'ouverture et de fermeture)
- Historique des compensations
- Séléction/Déselection de transaction pour une compensantion donnée


L'application s'appuie sur un ensemble de micro-service ou chaque microservice peut être développé, déployé et évoluer indépendamment :
**Service de Gestion des Transactions :**
- Responsable de la gestion des transactions entrantes et sortantes.
- Valide et stocke les transactions.
- Gère les états de transaction (en attente, confirmée, compensée, etc.).

**Service de Règles de Compensation :**
- Applique des règles de compensation sur les transactions en attente.
- Décide du moment opportun pour déclencher le processus de compensation.

**Service de gestion des établissements**
- CRUD sur 
  
**Service de paiement**
- Effectue le traitement financier réel des paiements compensés.
- Intégration avec des systèmes de paiement et de transfert de fonds.

**Service de notification**
- Envoie des notifications aux banques participantes sur l'état des transactions.
- Informe les utilisateurs des résultats de compensation.

### Architecture
![image](https://github.com/dthibau/bceao-cursus/assets/1072144/72626c18-2e91-46e3-adde-c65b98e80422)



