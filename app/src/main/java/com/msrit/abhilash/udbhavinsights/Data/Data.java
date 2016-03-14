package com.msrit.abhilash.udbhavinsights.Data;

import com.msrit.abhilash.udbhavinsights.R;

import java.util.ArrayList;

/**
 * Created by Abhilash on 13/03/2016.
 */
public class Data {

    public static String day1="30/03/2016 (day 1)";
    public static String day2="31/03/2016 (day 2)";
    public static String day3="01/04/2016 (day 3)";

    public static ArrayList<ItemData> getCategories()
    {
        final ArrayList<ItemData> categories = new ArrayList<>();
        categories.add( new ItemData("Literature",R.mipmap.icon2,getLitEvents()));
        categories.add( new ItemData("Art",R.mipmap.icon2,getArtEvents()));
        categories.add(new ItemData("Music", R.mipmap.icon2,getMusicEvents()));
        categories.add( new ItemData("Theatre",R.mipmap.icon2,getTheatreEvents()));
        categories.add( new ItemData("Dance",R.mipmap.icon2,getDanceEvents()));
        categories.add(new ItemData("Udbhav Cup",R.mipmap.icon2,getUdbhavCupEvents()));
        categories.add(new ItemData("Misc",R.mipmap.icon2,getMiscEvents()));
        return categories;
    }
    public static ArrayList<ItemData> getLitEvents() {
        final ArrayList<ItemData> LitEvents = new ArrayList<>();
        LitEvents.add(new ItemData("Inter-College events",0,null,true,0));
        LitEvents.add(new ItemData("Dumb Charades", R.mipmap.icon2,getLitEvent0Data(),true,150));
        LitEvents.add(new ItemData("Debate - English",R.mipmap.icon2,getLitEvent1Data(),true,100));
        LitEvents.add(new ItemData("General Quiz", R.mipmap.icon2,getLitEvent10Data(),true,150));
        LitEvents.add(new ItemData("Intra-College events",0,null,false,0));
        LitEvents.add(new ItemData("Debate - Kannada", R.mipmap.icon2,getLitEvent2Data(),false,0));
        LitEvents.add(new ItemData("Potpourri",  R.mipmap.icon2,getLitEvent3Data(),false,0));
        LitEvents.add(new ItemData("Elocution", R.mipmap.icon2,getLitEvent4Data(),false,0));
        LitEvents.add(new ItemData("Jam-Kannada",  R.mipmap.icon2,getLitEvent5Data(),false,0));
        LitEvents.add(new ItemData("Jam-English",  R.mipmap.icon2,getLitEvent6Data(),false,0));
        LitEvents.add(new ItemData("Creative Writing", R.mipmap.icon2,getLitEvent7Data(),false,0));
        LitEvents.add(new ItemData("Spent Quiz", R.mipmap.icon2,getLitEvent8Data(),false,0));
        LitEvents.add(new ItemData("Movie Quiz", R.mipmap.icon2,getLitEvent9Data(),false,0));

        return LitEvents;
    }


    public static ArrayList<ItemData> getArtEvents() {
        final ArrayList<ItemData> ArtEvents = new ArrayList<>();
        ArtEvents.add(new ItemData("Inter-College events",0,null,true,0));
        ArtEvents.add(new ItemData("On Spot Painting", R.mipmap.icon2,getArtEvent1Data(),true,100));
        ArtEvents.add(new ItemData("Collage",R.mipmap.icon2,getArtEvent2Data(),true,50));
        ArtEvents.add(new ItemData("Face Painting", R.mipmap.icon2,getArtEvent6Data(),true,100));
        ArtEvents.add(new ItemData("Intra-College events",0,null,false,0));
        ArtEvents.add(new ItemData("Cartooning", R.mipmap.icon2,getArtEvent0Data(),false,0));
        ArtEvents.add(new ItemData("By 2 Canvas", R.mipmap.icon2,getArtEvent3Data(),false,0));
        ArtEvents.add(new ItemData("Clay Modelling", R.mipmap.icon2,getArtEvent4Data(),false,0));
        ArtEvents.add(new ItemData("Rangoli", R.mipmap.icon2,getArtEvent5Data(),false,0));
        ArtEvents.add(new ItemData("Mehendi",  R.mipmap.icon2,getArtEvent7Data(),false,0));
        ArtEvents.add(new ItemData("Camouflage",  R.mipmap.icon2,getArtEvent8Data(),false,0));
        ArtEvents.add(new ItemData("Paper Couture",  R.mipmap.icon2,getArtEvent9Data(),false,0));
        ArtEvents.add(new ItemData("Poster Making", R.mipmap.icon2,getArtEvent10Data(),false,0));
        return ArtEvents;
    }


    public static ArrayList<ItemData> getMusicEvents() {
        final ArrayList<ItemData> MusicEvents = new ArrayList<>();
        MusicEvents.add(new ItemData("Inter-College events",0,null,true,0));
        MusicEvents.add(new ItemData("Western Group Music", R.mipmap.icon2,getMusicEvent0Data(),true,1000));
        MusicEvents.add(new ItemData("Indian Group Music", R.mipmap.icon2,getMusicEvent1Data(),true,500));
        MusicEvents.add(new ItemData("Western Acoustics Solo",  R.mipmap.icon2,getMusicEvent2Data(),true,100));
        MusicEvents.add(new ItemData("Western Acoustics Group",  R.mipmap.icon2,getMusicEvent3Data(),true,300));
        MusicEvents.add(new ItemData("Indian Light Music Solo", R.mipmap.icon2,getMusicEvent4Data(),true,150));
        MusicEvents.add(new ItemData("Classical Vocal Solo", R.mipmap.icon2,getMusicEvent5Data(),true,300));
        MusicEvents.add(new ItemData("Semi - Classical Vocal Solo", R.mipmap.icon2,getMusicEvent6Data(),true,50));
        MusicEvents.add(new ItemData("Classical Instrumental Solo",  R.mipmap.icon2,getMusicEvent7Data(),true,50));
        MusicEvents.add(new ItemData("Beat Boxing",  R.mipmap.icon2,getMusicEvent8Data(),true,100));
        MusicEvents.add(new ItemData("Intra-College events",0,null,false,0));
        MusicEvents.add(new ItemData("Antaakshari",  R.mipmap.icon2,getMusicEvent9Data(),false,0));
        MusicEvents.add(new ItemData("Karaoke", R.mipmap.icon2,getMusicEvent10Data(),false,0));

        return MusicEvents;
    }


    public static ArrayList<ItemData> getTheatreEvents() {
        final ArrayList<ItemData> TheatreEvents = new ArrayList<>();
        TheatreEvents.add(new ItemData("Inter-College events",0,null,true,0));
        TheatreEvents.add(new ItemData("One Act Play", R.mipmap.icon2,getTheatreEvent0Data(),true,500));
        TheatreEvents.add(new ItemData("Skit", R.mipmap.icon2,getTheatreEvent1Data(),true,200));
        TheatreEvents.add(new ItemData("Short Movies", R.mipmap.icon2,getTheatreEvent2Data(),true,300));
        TheatreEvents.add(new ItemData("Short Ads",  R.mipmap.icon2,getTheatreEvent3Data(),true,100));
        TheatreEvents.add(new ItemData("Street Play", R.mipmap.icon2,getTheatreEvent4Data(),true,300));
        return TheatreEvents;
    }

    public static ArrayList<ItemData> getDanceEvents() {
        final ArrayList<ItemData> DanceEvents = new ArrayList<>();
        DanceEvents.add(new ItemData("Inter-College events",0,null,true,0));
        DanceEvents.add(new ItemData("Dance As You Like", R.mipmap.icon2,getDanceEvent1Data(),true,100));
        DanceEvents.add(new ItemData("Indian Group Dance",R.mipmap.icon2,getDanceEvent2Data(),true,800));
        DanceEvents.add(new ItemData("Western Group Dance",R.mipmap.icon2,getDanceEvent3Data(),true,1000));
        DanceEvents.add(new ItemData("Street Dance 3x3",  R.mipmap.icon2,getDanceEvent4Data(),true,500));
        DanceEvents.add(new ItemData("Street Dance 2x2",  R.mipmap.icon2,getDanceEvent5Data(),true,300));
        DanceEvents.add(new ItemData("Street Dance 7 to smoke",  R.mipmap.icon2,getDanceEvent6Data(),true,150));
        DanceEvents.add(new ItemData("Intra-College events",0,null,false,0));
        DanceEvents.add(new ItemData("Classical Dance Solo", R.mipmap.icon2,getDanceEvent0Data(),false,0));
        return DanceEvents;
    }

    public static ArrayList<ItemData> getUdbhavCupEvents() {
        final ArrayList<ItemData> UdbhavCupEvents = new ArrayList<>();
        UdbhavCupEvents.add(new ItemData("Inter-College events",0,null,true,0));
        UdbhavCupEvents.add(new ItemData("Badminton", R.mipmap.icon2,getUdbCupEvent0Data(),true,0));
        UdbhavCupEvents.add(new ItemData("Basketball",  R.mipmap.icon2,getUdbCupEvent1Data(),true,0));
        UdbhavCupEvents.add(new ItemData("Cricket",  R.mipmap.icon2,getUdbCupEvent2Data(),true,0));
        UdbhavCupEvents.add(new ItemData("Football", R.mipmap.icon2,getUdbCupEvent3Data(),true,0));
        return UdbhavCupEvents;
    }

    public static ArrayList<ItemData> getMiscEvents() {
        final ArrayList<ItemData> MiscEvents = new ArrayList<>();
        MiscEvents.add(new ItemData("Inter-College events",0,null,true,0));
        MiscEvents.add(new ItemData("On The Spot Photography", R.mipmap.icon2, getMiscEvent4Data(),true,100));
        MiscEvents.add(new ItemData("Fifa 14",  R.mipmap.icon2, getMiscEvent5Data(),true,50));
        MiscEvents.add(new ItemData("Dota 2",  R.mipmap.icon2, getMiscEvent6Data(),true,250));
        MiscEvents.add(new ItemData("Counter Strike 1.6 ", R.mipmap.icon2, getMiscEvent7Data(),true,250));
        MiscEvents.add(new ItemData("Intra-College events",0,null,false,0));
        MiscEvents.add(new ItemData("Personality", R.mipmap.icon2, getMiscEvent0Data(),false,0));
        MiscEvents.add(new ItemData("Cooking",  R.mipmap.icon2, getMiscEvent1Data(),false,0));
        MiscEvents.add(new ItemData("Treasure Hunt",  R.mipmap.icon2, getMiscEvent2Data(),false,0));
        MiscEvents.add(new ItemData("Msrit’s Got Talent",  R.mipmap.icon2, getMiscEvent3Data(),false,0));

        return MiscEvents;
    }

    public static EventData getLitEvent0Data()
    {
        EventData Event = new EventData();
        Event.setEvent_name("Dumb Charades");
        Event.setInter(true);
        Event.setSize(3);
        Event.setDate("31/03/2016 (day 2)");
        Event.setTime("09:00 am");
        Event.setVenue("Class room 1");
        Event.setCoordinator("Alvin Mark");
        Event.setPhone("9880716414");
        return Event;
    }


    public static EventData getLitEvent1Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Debate - English");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate("01/04/2016 (day 3)");
        Event0.setTime("09:00 am");
        Event0.setVenue("LHC Seminar Hall 2");
        Event0.setCoordinator("Nishank Kukrejha");
        Event0.setPhone("9742032756");
        return Event0;
    }

    public static EventData getLitEvent2Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Debate - Kannada");
        Event0.setSize(2);
        Event0.setDate("30/03/2016 (day 1)");
        Event0.setTime("09:00 am");
        Event0.setVenue("LHC Seminar Hall 1");
        Event0.setCoordinator("Neha M Nayak");
        Event0.setPhone("");
        return Event0;
    }
    public static EventData getLitEvent3Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Pot Pourri");
        Event0.setSize(3);
        Event0.setDate("30/03/2016 (day 1)");
        Event0.setTime("09:00 am");
        Event0.setVenue("Apex Block Auditorium");
        Event0.setCoordinator("Shreya");
        Event0.setPhone("9980307321");
        return Event0;
    }

    public static EventData getLitEvent4Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Elocution");
        Event0.setSize(1);
        Event0.setDate("30/03/2016 (day 1)");
        Event0.setTime("12:00 pm");
        Event0.setVenue("LCH Seminar Hall 1");
        Event0.setCoordinator("");
        Event0.setPhone("");
        return Event0;
    }

    public static EventData getLitEvent5Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Jam-Kannada");
        Event0.setSize(1);
        Event0.setDate("30/03/2016 (day 1)");
        Event0.setTime("09:00 am");
        Event0.setVenue("ESB Small Seminar Hall");
        Event0.setCoordinator("Supreeth");
        Event0.setPhone("8904430345");
        Event0.setRules("\n \n • Contestants are allowed to participate alone only" +
                        "\n \n • Medium of expression will be Kannada only" +
                        "\n \n • Number of rounds and number of contestants in each round is up to the jam master" +
                        "\n \n • Contestants may not use vulgar form of expression in the event"
        );
        return Event0;
    }
    public static EventData getLitEvent6Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Jam-English");
        Event0.setSize(1);
        Event0.setDate("30/03/2016 (day 1)");
        Event0.setTime("01:00 pm");
        Event0.setVenue("ESB Small Seminar Hall");
        Event0.setCoordinator("Vishvesh Sriram");
        Event0.setPhone("");
        return Event0;
    }

    public static EventData getLitEvent7Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Creative Writing");
        Event0.setSize(1);
        Event0.setDate("30/03/2016 (day 1)");
        Event0.setTime("09:00 am");
        Event0.setVenue("Class Room 1");
        Event0.setCoordinator("");
        Event0.setPhone("");
        return Event0;
    }

    public static EventData getLitEvent8Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Spent Quiz");
        Event0.setSize(3);
        Event0.setDate("30/03/2016 (day 1)");
        Event0.setTime("02:00 pm");
        Event0.setVenue("Hi-Tech Seminar Hall");
        Event0.setCoordinator("Micheal Albuquerque");
        Event0.setPhone("8095270184");
        return Event0;
    }

    public static EventData getLitEvent9Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Movie Quiz");
        Event0.setSize(3);
        Event0.setDate("31/03/2016 (day 2)");
        Event0.setTime("09:00 am");
        Event0.setVenue("LHC Seminar Hall 2");
        Event0.setCoordinator("Tanoy Dutta");
        Event0.setPhone("9742031736");
        return Event0;
    }

    public static EventData getLitEvent10Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("General Quiz");
        Event0.setInter(true);
        Event0.setSize(3);
        Event0.setDate("01/04/2016 (day 3)");
        Event0.setTime("09:00 am");
        Event0.setVenue("Apex Auditorium");
        Event0.setCoordinator("Vasant Menon");
        Event0.setPhone("9986145477");
        return Event0;
    }


    public static EventData getArtEvent0Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Cartooning");
        Event0.setSize(1);
        Event0.setDate("01/04/2016 (day 3)");
        Event0.setTime("12:00 pm");
        Event0.setVenue("LHC Seminar Hall 1");
        Event0.setCoordinator("Monisha");
        Event0.setPhone("9880850665");
        return Event0;
    }

    public static EventData getArtEvent1Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("On Spot Painting");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate("01/04/2016 (day 3)");
        Event0.setTime("09:00 am");
        Event0.setVenue("ESB II stage");
        Event0.setCoordinator("Thressia");
        Event0.setPhone("9886495776");
        return Event0;
    }

    public static EventData getArtEvent2Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Collage");
        Event0.setInter(true);
        Event0.setSize(2);
        Event0.setDate("31/03/2016 (day 2)");
        Event0.setTime("01:00 pm");
        Event0.setVenue("LHC stage");
        Event0.setCoordinator("Monisha");
        Event0.setPhone("9880850665");
        return Event0;
    }

    public static EventData getArtEvent3Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("By 2 Canvas");
        Event0.setSize(2);
        Event0.setDate("30/03/2016 (day 1)");
        Event0.setTime("12:00 pm");
        Event0.setVenue("ESB 2 basement");
        Event0.setCoordinator("Srivastav");
        Event0.setPhone("9483466019");
        return Event0;
    }

    public static EventData getArtEvent4Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Clay Modelling");
        Event0.setSize(1);
        Event0.setDate("01/04/2016 (day 3)");
        Event0.setTime("12:00 pm");
        Event0.setVenue("ESB 2 stage");
        Event0.setCoordinator("Greeshma");
        Event0.setPhone("");
        return Event0;
    }

    public static EventData getArtEvent5Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Rangoli");
        Event0.setSize(2);
        Event0.setDate("31/03/2016 (day 2)");
        Event0.setTime("09:00 am");
        Event0.setVenue("ESB 2 basement");
        Event0.setCoordinator("Attreyee");
        Event0.setPhone("9731760093");
        return Event0;
    }

    public static EventData getArtEvent6Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Face Painting");
        Event0.setInter(true);
        Event0.setSize(2);
        Event0.setDate("31/03/2016 (day 2)");
        Event0.setTime("09:00 am");
        Event0.setVenue("Amphi Theatre");
        Event0.setCoordinator("Anju Balakrishnan");
        Event0.setPhone("8197787574");
        return Event0;
    }

    public static EventData getArtEvent7Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Mehendi");
        Event0.setSize(2);
        Event0.setDate("30/03/2016 (day 1)");
        Event0.setTime("03:00 pm");
        Event0.setVenue("LHC Stage");
        Event0.setCoordinator("Thressia");
        Event0.setPhone("9886495776");
        return Event0;
    }

    public static EventData getArtEvent8Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Camouflage");
        Event0.setSize(2);
        Event0.setDate("30/03/2016 (day 1)");
        Event0.setTime("09:00 am");
        Event0.setVenue("LHC Stage");
        Event0.setCoordinator("Attreyee");
        Event0.setPhone("9731760093");
        return Event0;
    }

    public static EventData getArtEvent9Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Paper Couture");
        Event0.setSize(2);
        Event0.setDate("01/04/2016 (day 3)");
        Event0.setTime("12:00 pm");
        Event0.setVenue("LHC Stage");
        Event0.setCoordinator("Anju");
        Event0.setPhone("8197787574");
        return Event0;
    }

    public static EventData getArtEvent10Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Poster Making");
        Event0.setSize(2);
        Event0.setDate("30/03/2016 (day 1)");
        Event0.setTime("10:00 am");
        Event0.setVenue("ESB 2 Stage");
        Event0.setCoordinator("Anju");
        Event0.setPhone("8197787574");
        return Event0;
    }

    public static EventData getMusicEvent0Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Western Group Music");
        Event0.setInter(true);
        Event0.setSize(12);     //todo doubt
        Event0.setDate(day3);
        Event0.setTime("12:00 pm");
        Event0.setVenue("Main Stage");
        Event0.setCoordinator("Harish Jayanth");
        Event0.setPhone("9986667984");
        return Event0;
    }

    public static EventData getMusicEvent1Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Indian Group Music");
        Event0.setInter(true);
        Event0.setSize(6);  //todo doubt
        Event0.setDate(day1);
        Event0.setTime("03:00 pm");
        Event0.setVenue("Main Stage");
        Event0.setCoordinator("Raghav");
        Event0.setPhone("9611745137");
        return Event0;
    }

    public static EventData getMusicEvent2Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Western Acoustics Solo");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate(day2);
        Event0.setTime("09:00 am");
        Event0.setVenue("Hi Tech Semminar Hall");
        Event0.setCoordinator("Samarth Mamadapur");
        Event0.setPhone("9902500902");
        return Event0;
    }

    public static EventData getMusicEvent3Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Western Acoustics Group");
        Event0.setInter(true);
        Event0.setSize(8);
        Event0.setDate(day2);
        Event0.setTime("01:00 pm");
        Event0.setVenue("Hi Tech Semminar Hall");
        Event0.setCoordinator("Samarth Mamadapur");
        Event0.setPhone("9902500902");
        return Event0;
    }

    public static EventData getMusicEvent4Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Indian Light Music Solo");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate(day1);
        Event0.setTime("09:00 am");
        Event0.setVenue("Amphi Theatre");
        Event0.setCoordinator("Shriya");
        Event0.setPhone("9632413752");
        return Event0;
    }

    public static EventData getMusicEvent5Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Classical Vocal Solo");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate(day2);
        Event0.setTime("12:00 pm");
        Event0.setVenue("LHC Seminar Hall 1");
        Event0.setCoordinator("Pramod Gabbur");
        Event0.setPhone("8147650695");
        return Event0;
    }

    public static EventData getMusicEvent6Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Semi - Classical Vocal Solo");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate(day2);
        Event0.setTime("02:00 pm");
        Event0.setVenue("LHC Seminar Hall 1");
        Event0.setCoordinator("Pramod Gabbur");
        Event0.setPhone("8147650695");
        return Event0;
    }

    public static EventData getMusicEvent7Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Classical Instrumental Solo");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate(day2);
        Event0.setTime("09:00 am");
        Event0.setVenue("LHC Seminar Hall 1");
        Event0.setCoordinator("Ankit Verma");
        Event0.setPhone("8867831648");
        return Event0;
    }

    public static EventData getMusicEvent8Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Beat Boxing");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate(day2);
        Event0.setTime("12:00 pm");
        Event0.setVenue("ESB 2 stage");
        Event0.setCoordinator("Suraj Suresh");
        Event0.setPhone("9035120278");
        return Event0;
    }

    public static EventData getMusicEvent9Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Antaakshari");
        Event0.setSize(2);
        Event0.setDate(day1);
        Event0.setTime("01:00 pm");
        Event0.setVenue("ESB 2 stage");
        Event0.setCoordinator("Nandita");
        Event0.setPhone("9945045100");
        return Event0;
    }

    public static EventData getMusicEvent10Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Karaoke");
        Event0.setSize(1);
        Event0.setDate(day1);
        Event0.setTime("02:00 pm");
        Event0.setVenue("ESB Big Seminar Hall");
        Event0.setCoordinator("Niranjan");
        Event0.setPhone("9741639796");
        return Event0;
    }


    public static EventData getTheatreEvent0Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("One Act Play");
        Event0.setInter(true);
        Event0.setSize(12);
        Event0.setDate(day2);
        Event0.setTime("09:00 am");
        Event0.setVenue("Apex Block Auditorium");
        Event0.setCoordinator("Pavan Kamat");
        Event0.setPhone("");
        return Event0;
    }

    public static EventData getTheatreEvent1Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Skit");
        Event0.setInter(true);
        Event0.setSize(9);
        Event0.setDate(day3);
        Event0.setTime("10:00 am");
        Event0.setVenue("ESB Small Seminar Hall");
        Event0.setCoordinator("Supreeth HS");
        Event0.setPhone("8904430345");
        return Event0;
    }

    public static EventData getTheatreEvent2Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Short Movies");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate(day2);
        Event0.setTime("09:00 am");
        Event0.setVenue("ESB Small Seminar Hall");
        Event0.setCoordinator("Mani Bharathi");
        Event0.setPhone("");
        return Event0;
    }

    public static EventData getTheatreEvent3Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Short Ads");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate(day2);
        Event0.setTime("04:00 pm");
        Event0.setVenue("ESB Small Seminar Hall");
        Event0.setCoordinator("Mani Bharathi");
        Event0.setPhone("");
        return Event0;
    }

    public static EventData getTheatreEvent4Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Street Play");
        Event0.setInter(true);
        Event0.setSize(12);
        Event0.setDate(day3);
        Event0.setTime("03:00 pm");
        Event0.setVenue("Parking lot stage");
        Event0.setCoordinator("Muhammed Sufail");
        Event0.setPhone("9036049324");
        return Event0;
    }


    public static EventData getDanceEvent0Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Classical Dance Solo");
        Event0.setSize(1);
        Event0.setDate(day3);
        Event0.setTime("10:00 am");
        Event0.setVenue("Hi-Tech Seminar Hall");
        Event0.setCoordinator("Nidhi Madhusudhan");
        Event0.setPhone("9740077479");
        return Event0;
    }

    public static EventData getDanceEvent1Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Dance As You Like");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate(day2);
        Event0.setTime("12:00 pm");
        Event0.setVenue("Law Canteen");
        Event0.setCoordinator("Aishwarya Rajan");
        Event0.setPhone("9845597663");
        return Event0;
    }

    public static EventData getDanceEvent2Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Indian Group Dance");
        Event0.setInter(true);
        Event0.setSize(15);
        Event0.setDate(day2);
        Event0.setTime("10:00 am");
        Event0.setVenue("Main Stage");
        Event0.setCoordinator("Shreya Nair");
        Event0.setPhone("8861862967");
        return Event0;
    }

    public static EventData getDanceEvent3Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Western Group Dance");
        Event0.setInter(true);
        Event0.setSize(15);
        Event0.setDate(day2);
        Event0.setTime("02:00 pm");
        Event0.setVenue("Main Stage");
        Event0.setCoordinator("Vivek Chandru");
        Event0.setPhone("8553717447");
        return Event0;
    }

    public static EventData getDanceEvent4Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Street Dance 3x3");
        Event0.setInter(true);
        Event0.setSize(3);
        Event0.setDate(day3);
        Event0.setTime("11:00 am");
        Event0.setVenue("Law canteen");
        Event0.setCoordinator("Ankush Shetty");
        Event0.setPhone("9686487110");
        return Event0;
    }

    public static EventData getDanceEvent5Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Street Dance 2x2");
        Event0.setInter(true);
        Event0.setSize(2);
        Event0.setDate(day3);
        Event0.setTime("11:00 am");
        Event0.setVenue("Law canteen");
        Event0.setCoordinator("Ankush Shetty");
        Event0.setPhone("9686487110");
        return Event0;
    }


    public static EventData getDanceEvent6Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Street Dance 7 to smoke");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate(day3);
        Event0.setTime("11:00 am");
        Event0.setVenue("Law canteen");
        Event0.setCoordinator("Ankush Shetty");
        Event0.setPhone("9686487110");
        return Event0;
    }



    public static EventData getUdbCupEvent0Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Badminton");
        Event0.setSize(1);  //todo doubt
        Event0.setDate("31/03/2016");
        Event0.setTime("09:00 am");
        Event0.setVenue("LHC Seminar Hall 2");
        Event0.setCoordinator("Tanoy Dutta");
        Event0.setPhone("9742031736");
        return Event0;
    }

    public static EventData getUdbCupEvent1Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Basketball");
        Event0.setSize(1);  //todo doubt
        Event0.setDate("31/03/2016");
        Event0.setTime("09:00 am");
        Event0.setVenue("LHC Seminar Hall 2");
        Event0.setCoordinator("Tanoy Dutta");
        Event0.setPhone("9742031736");
        return Event0;
    }

    public static EventData getUdbCupEvent2Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Cricket");
        Event0.setSize(1);  //todo doubt
        Event0.setDate("31/03/2016");
        Event0.setTime("09:00 am");
        Event0.setVenue("LHC Seminar Hall 2");
        Event0.setCoordinator("Tanoy Dutta");
        Event0.setPhone("9742031736");
        return Event0;
    }

    public static EventData getUdbCupEvent3Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Football");
        Event0.setSize(1);  //todo doubt
        Event0.setDate("31/03/2016");
        Event0.setTime("09:00 am");
        Event0.setVenue("LHC Seminar Hall 2");
        Event0.setCoordinator("Tanoy Dutta");
        Event0.setPhone("9742031736");
        return Event0;
    }


    public static EventData getMiscEvent0Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Personality");
        Event0.setSize(1);
        Event0.setDate(day1);
        Event0.setTime("10:00 am");
        Event0.setVenue("Pit Stop");
        Event0.setCoordinator("Utsav");
        Event0.setPhone("9620479454");
        return Event0;
    }

    public static EventData getMiscEvent1Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Cooking");
        Event0.setSize(2);
        Event0.setDate(day3);
        Event0.setTime("09:00 am");
        Event0.setVenue("ESB 2 basement");
        Event0.setCoordinator("Moheez Mushtaq");
        Event0.setPhone("9591212477");
        return Event0;
    }

    public static EventData getMiscEvent2Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Treasure Hunt");
        Event0.setSize(3);
        Event0.setDate(day2);
        Event0.setTime("11:00 am");
        Event0.setVenue("Pit Stop");
        Event0.setCoordinator("Aditi Hudli");
        Event0.setPhone("");
        return Event0;
    }

    public static EventData getMiscEvent3Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Msrit’s Got Talent");
        Event0.setSize(1);
        Event0.setDate(day1);
        Event0.setTime("12:00 pm");
        Event0.setVenue("Main Stage");
        Event0.setCoordinator("");
        Event0.setPhone("");
        return Event0;
    }

    public static EventData getMiscEvent4Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("On The Spot Photography");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate(day2);
        Event0.setTime("09:00 am");
        Event0.setVenue("Pit Stop");
        Event0.setCoordinator("Rahul Ravindran");
        Event0.setPhone("");
        return Event0;
    }

    public static EventData getMiscEvent5Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Fifa 14");
        Event0.setInter(true);
        Event0.setSize(1);
        Event0.setDate(day2);
        Event0.setTime("11:00 am");
        Event0.setVenue("Lab, DES Block");
        Event0.setCoordinator("Saksham");
        Event0.setPhone("");
        return Event0;
    }

    public static EventData getMiscEvent6Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Dota 2");
        Event0.setInter(true);
        Event0.setSize(5);
        Event0.setDate(day2);
        Event0.setTime("11:00 am");
        Event0.setVenue("Lab1, DES Block");
        Event0.setCoordinator("");
        Event0.setPhone("");
        return Event0;
    }

    public static EventData getMiscEvent7Data()
    {
        EventData Event0 = new EventData();
        Event0.setEvent_name("Counter Strike 1.6");
        Event0.setInter(true);
        Event0.setSize(5);
        Event0.setDate(day3);
        Event0.setTime("10:00 am");
        Event0.setVenue("Lab1, DES Block");
        Event0.setCoordinator("Krunal Bhatt");
        Event0.setPhone("9535114490");
        return Event0;
    }

}
