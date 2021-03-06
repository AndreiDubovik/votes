package by.ormedia.vote.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import by.ormedia.vote.entity.Item;
import by.ormedia.vote.entity.Subject;
import by.ormedia.vote.entity.User;
import by.ormedia.vote.entity.VoteTicket;

public class JSONUtils {
	
	public static Subject getSubjectFromJSON(String json){
		JSONParser parser = new JSONParser();
		Subject subject = new Subject();
		try {
			JSONObject ob = (JSONObject)parser.parse(json);
			String creatorName = (String)ob.get("creator");
			User creator = new User();
			creator.setEmail(creatorName);
			subject.setInitiator(creator);
			JSONObject sub = (JSONObject)ob.get("subject");
			subject.setSubject((String)sub.get("subjectName"));
			JSONArray items = (JSONArray)sub.get("items");
			@SuppressWarnings("rawtypes")
			Iterator it = items.iterator();
			Set<Item>set = new HashSet<>();
			while(it.hasNext()){
				Item item = new Item();
				Object itemString = it.next();
				item.setText((String)itemString);
				item.setSubject(subject);
				set.add(item);
			}
			JSONArray users = (JSONArray)ob.get("users");
			it = users.iterator();
			Set<VoteTicket>tickets = new HashSet<>();
			while(it.hasNext()){
				VoteTicket ticket = new VoteTicket();
				User user = new User();
				user.setEmail((String)it.next());
				ticket.setUser(user);
				ticket.setSubject(subject);
				String key = Long.toHexString((long)(Long.MAX_VALUE*Math.random()));
				ticket.setKeycode(key);
				tickets.add(ticket);
			}
			subject.setVoteTickets(tickets);
			subject.setItems(set);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return subject;
	}

}
