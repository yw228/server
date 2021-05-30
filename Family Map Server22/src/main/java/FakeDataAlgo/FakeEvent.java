package FakeDataAlgo;

import FakeDataAlgo.FakeLocation;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import Models.Event;
import Models.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class FakeEvent {
  FakeLocation fakeLocation = new FakeLocation();
  Random random = new Random();
  String username = null;
  ArrayList<Event> events = new ArrayList<Event>();

  public String EventType() {
    try {
      Random r = new Random();
      FileReader fr = new FileReader(new File("json/enames.json"));
      JsonParser jp = new JsonParser();
      JsonObject jo = (JsonObject) jp.parse(fr);
      JsonArray ja = (JsonArray) jo.get("data");

      int i = r.nextInt(ja.size());
      String event = ja.get(i).toString();
      event = event.substring(1, event.length() - 1);
      return event;

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void Marriage(Person husband, Person wife, int year) {
    int yearMarried = year + random.nextInt(6) + 22;

    Event husbandMarriage = fakeLocation.FakeLocation();
    husbandMarriage.setPersonID(husband.getPersonID());
    husbandMarriage.setAssociatedUsername(username);
    husbandMarriage.setEventType("Marriage");
    husbandMarriage.setYear(yearMarried);

    Event wifeMarriage = JoinMarriage(husbandMarriage, wife, yearMarried);

    events.add(husbandMarriage);
    events.add(wifeMarriage);
  }

  public void RootBirth(Person rootPerson, int year) {
    Event birth =fakeLocation.FakeLocation();;

    birth.setPersonID(rootPerson.getPersonID());
    birth.setEventType("Birth");
    birth.setYear(year);
    birth.setAssociatedUsername(username);

    events.add(birth);
  }

  public void Birth(Person person, int year) {
    Event birth =fakeLocation.FakeLocation();
    int yearBorn = year - random.nextInt(10);

    birth.setPersonID(person.getPersonID());
    birth.setEventType("Birth");
    birth.setYear(yearBorn);
    birth.setAssociatedUsername(username);

    events.add(birth);
  }

  public void Death(Person person, int year) {
    Event death =fakeLocation.FakeLocation();
    int avgLifespan = 30;
    int yearDeath = year + avgLifespan + random.nextInt(50);
    if ((yearDeath - year) > 120) {
      yearDeath = year + 120;
    }

    death.setPersonID(person.getPersonID());
    death.setEventType("Death");
    death.setYear(yearDeath);
    death.setAssociatedUsername(username);

    events.add(death);
  }

  public void Random(Person person, int year) {
    int yearsBeforeBirth = 10;
    int eventYear = year + yearsBeforeBirth + random.nextInt(20);

    Event random =fakeLocation.FakeLocation();
    random.setPersonID(person.getPersonID());
    random.setEventType(EventType());
    random.setYear(eventYear);
    random.setAssociatedUsername(username);

    events.add(random);
  }

  private Event JoinMarriage(Event husbandMarriage, Person wife, int yearMarried) {
    Event wifeMarriage = new Event();

    wifeMarriage.setPersonID(wife.getPersonID());
    wifeMarriage.setAssociatedUsername(username);
    wifeMarriage.setEventType("Marriage");
    wifeMarriage.setYear(yearMarried);
    wifeMarriage.setLongitude(husbandMarriage.getLongitude());
    wifeMarriage.setLatitude(husbandMarriage.getLatitude());
    wifeMarriage.setCity(husbandMarriage.getCity());
    wifeMarriage.setCountry(husbandMarriage.getCountry());

    return wifeMarriage;
  }


  public FakeEvent(String setUsername) { username = setUsername; }

  public ArrayList<Event> GetEvents() { return events; }
}
