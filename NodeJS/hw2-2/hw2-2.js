var MongoClient = require('mongodb').MongoClient;

MongoClient.connect('mongodb://localhost:27017/weather', function(err, db){
	if(err) throw err;

	var query = {};

	var data = db.collection('data');

	var cursor = data.find({});
	cursor.sort([['State',1],['Temperature',-1]]);

	var State = "";
	cursor.each(function(err,doc){
		if(err) throw err;

		if(doc == null){
			return db.close();
		}

		if(doc['State'] != State || State == ""){
			State = doc['State'];
			
			//doc['month_high'] = true;
			var operator = {'$set':{'month_high':true}};
			query['_id'] = doc['_id'];

			db.collection('data').update(query, operator, function(err, updated){
				console.dir(doc);
			});
		}

	});
	
});
