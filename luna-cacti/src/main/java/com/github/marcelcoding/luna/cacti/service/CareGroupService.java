package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.api.CareGroup;
import com.github.marcelcoding.luna.cacti.api.CareGroup.Time;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public final class CareGroupService {

  private final Map<String, CareGroup> careGroups;

  public CareGroupService() {
    this.careGroups = List.of(
      new CareGroup(
        "1",
        "Pflegegruppe 1",
        "tropische Urwälder, feuchtwarmes Klima",
        "Humusboden, pH 4.5 - 5.5, für Hydrokultur geeignet",
        new Time(null, "feuchtwarmer Stand", null, null, null),
        new Time(null, null, "über 10°C", "mäßig feucht", null)
      ),

      new CareGroup(
        "2",
        "Pflegegruppe 2",
        "Savannen, lichte Wälder",
        "Humusboden, pH 4.5 - 5.5, für Hydrokultur geeignet",
        new Time("halbsonnig bis vollsonnig", null, "warm", "feucht", null),
        new Time(null, null, "min. 8°C", "trocken", null)
      ),

      new CareGroup("3",
        "Pflegegruppe 3", "Steppe & Gebirge", "lehmig-kiesig, schwach sauer, pH 4.5 - 6",
        new Time("sonnig", "freier Stand", null, "kaum nässeempfindlich", null),
        new Time(null, "trockener Stand", "nicht unter 0°C", null, null)
      ),

      new CareGroup(
        "3a",
        "Pflegegruppe 3a",
        "kühles Klima",
        "kiesig-lehmig, pH 4.5 - 6",
        new Time("sonnig", "freier Stand", null, null, null),
        new Time(null, "trockender Stand", "kälteverträglich, bei geeignetem Substrat bis -25°C", null,
          "Überdauern den Winter unter einer Schneeecke sehr gut, gefährlich sind Kahlfröste nach vorhergegangener nasser Wärmeperiode,"
          + "abdecken mit Reisig oder Brettern, auch überwintern in trockenem Frühbeetkasten mit Sonnenschutz")
      ),

      new CareGroup(
        "4",
        "Pflegegruppe 4",
        "warme Steppe",
        "sandig-kiesig, humusarm, pH 5 - 6",
        new Time("sonnig", "möglichtst freier Stand", "warmer Stand", "meist etwas nässeempfindlich", null),
        new Time(null, null, "min. 5°C", "trocken", null)
      ),

      new CareGroup(
        "5",
        "Pflegegruppe 5",
        "warme Trockengebiete",
        "kiesig, rein mineralisch, pH 5.6 - 6.5, Pfropfung zu emppfehlen",
        new Time("vollsonnig", "warmer Stand", null, "nässeempfindlich, Wurzelhals trocken halten", null),
        new Time("möglichst hell", null, "min. 4°C", "trocken", null)
      ),

      new CareGroup(
        "6",
        "Pflegegruppe 6",
        "sehr warme Steppen",
        "kiesig-lehmig, pH 5 - 6",
        new Time("sonnig", null, "warm", "nässeempfindlich", null),
        new Time(null, null, "min. 8°C", "trocken bis schwach feucht", null)
      ),

      new CareGroup(
        "7",
        "Pflegegruppe 7",
        "Gebirge, Hochgebirge & Steppen",
        "unterschiedliche Mischungen & pH Werte, deshalb in Pflegegruppe 7a & 7b unterteilt",
        new Time("sonnig", "luftig, freier Stand", null, null, null),
        new Time(null, null, null, null,
          "unterschiedliche Temperaturansprüche, deshalb in Pflegegruppe 7a & 7b unterteilt")
      ),

      new CareGroup(
        "7a",
        "Pflegegruppe 7a",
        "Gebirge, Hochgebirge",
        "sandig-lehmig, schwach sauer, pH 4.5 - 6",
        new Time("sonnig", "freier Stand", null, "kaum nässeempfindlich", null),
        new Time("hell", "luftig", "min. 3°C & möglichst nicht über 20°C", "trocken", null)
      ),

      new CareGroup(
        "7b",
        "Pflegegruppe 7b", "Steppen & Gebirge", "sandig-mineralisch mit etwas Humusgehalt, leicht sauer, pH 5 - 6.5",
        new Time("sonnig, Schutz vor Prallsonne (besonders bei Jungpflanzen)", "luftig, zeitweise ohne Glasbedeckung",
          "genügend, bei Wärme nicht zu wenig Feuchtichkeit", null, null),
        new Time("hell", null, "kühl, 8 - 12°C, Gebirgsarten auch darunter, andere nur Kurzfistig darunter",
          "ziemlich trocken, Jungpflanzen und Veredlungen brauchen zur Erhaltung der Faserwurzeln ständig ein geringes Quantum Feuchtigkeit von unten",
          null)
      ),

      new CareGroup(
        "8",
        "Pflegegruppe 8", "Küsten- bis Gebirgsgegenden & Nebelzonen",
        "mineralisch-kiesig, pH 5 - 6, Pfropfung zum Teil zu empfehlen",
        new Time("hell, im Sommer halb- bis vollsonnig, Herbst bis Frühjahr vollsonnig", "reichlich lüften",
          "ausreichend Wärme",
          "ausreichnd Feuchtichkeit", null),
        new Time("hell", "lufttrocken", "warm, nicht unter 10°C", null, null)
      )
    ).stream().collect(Collectors.toUnmodifiableMap(CareGroup::getId, Function.identity()));
  }

  public Collection<CareGroup> findAll() {
    return this.careGroups.values();
  }

  public Optional<CareGroup> findById(final String id) {
    return Optional.ofNullable(this.careGroups.get(id));
  }

  public boolean exist(final String id) {
    return this.careGroups.containsKey(id);
  }
}
