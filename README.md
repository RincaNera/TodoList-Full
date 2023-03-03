TP JAX-RS :  

Gestion complète d’une todo list avec BDD

Définition d’une TODO :
- Nom
- Description
- Date
- Urgence (id)
- Utilisateur (id)  

Définition d’un utilisateur
- Nom
- Prenom

Définition d’une Urgence
- Nom  

CREER UN CRUD POUR -> TODO / UTILISATEUR / URGENCE 
Votre programme permet de récupérer une liste de todo :
- Par utilisateur
- Par urgence
- Trié par urgence, par utilisateur

Permet la gestion des urgences :

On peut pas supprimer une urgence ou il reste des todo dans la base de données.(Génère une erreur -> gérer la response avec le le status code)

Le programme permet de rajouter des todos :

Par id utilisateur
Par nom prénom utilisateur

Si l’utilisateur n’existe pas renvoyer un status code d’erreur

On peut supprimé un utilisateur et cela supprimera toutes ses todos.

Priorité a faire tout cela fonctionnant avec POSTMAN

————————————————————— BONUS ————————————————————— 

Créer une partie d’un client sur une page HTML avec les ressources de mdn. 

Niveau 1 ) Des formulaires HTML de saisie

Niveau 2) Gérer la récupération des réponses en javascript (juste afficher sur la console)======> utiliser fetch();

REF : https://developer.mozilla.org/fr/docs/Web/API/Fetch_API/Using_Fetch
