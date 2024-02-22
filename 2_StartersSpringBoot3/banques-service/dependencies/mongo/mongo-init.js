db = db.getSiblingDB('workflow');

db.createCollection('workflow');

db.workflow.insertMany([
 {
   "_id": "BANQUES",
   "name": "Gestion du vie des établissements",
   "states": [
     {
       "name": "CREATED"
     },
     {
         "name": "REJECTED"
     },
     {
        "name": "VALIDATED"
     }
   ],
   "transitions": [
     {
       "action": "CREATE",
       "sourceState": "",
       "targetState": "CREATED"
     },
     {
       "action": "REJECT",
       "sourceState": "CREATED",
       "targetState": "REJECTED"
     },
     {
       "action": "VALIDATE",
       "sourceState": "CREATED",
       "targetState": "VALIDATED"
     }
   ]
 }
]);