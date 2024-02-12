db = db.getSiblingDB('workflow');

db.createCollection('workflow');

db.workflow.insertMany([
 {
   "_id": "BANQUES",
   "name": "Gestion du vie des établissements",
   "states": [
     {
       "name": "CREATED"
     }
   ],
   "transitions": [
     {
       "action": "CREATE",
       "sourceState": "",
       "targetState": "CREATED"
     }
   ]
 }
]);