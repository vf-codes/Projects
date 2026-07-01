import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

class Song{
    private int id;
    private String name;
    private int playCount = 0;
    private boolean isFavourite = false;
        
    
    public Song(int id, String name){
        this.id = id;
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
    
        if (this == obj) {
            return true;
        }
    
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
    
        Song other = (Song) obj;
    
        return Objects.equals(this.id, other.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    public int getSongID(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public int getPlayCount(){
        return playCount;
    }
    
    public boolean getFavouriteStatus(){
        return isFavourite;
    }
    
    public void increasePlayCount(){
        playCount++;
    }
    
    public boolean markFavourite(){
        if (!isFavourite){
            isFavourite = true;
            return true;
        }
        return false;
    }
    
    public boolean unmarkFavourite(){
        if (isFavourite){
            isFavourite = false;
            return true;
        }
        return false;
    }
    
}

class Playlist{
    private List<Song> songs = new ArrayList<>();
    private String name;
    
    public Playlist(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public List<Song> getSongsList(){
        return songs;
    }
    
    @Override
    public boolean equals(Object obj) {
    
        if (this == obj) {
            return true;
        }
    
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
    
        Playlist other = (Playlist) obj;
    
        return Objects.equals(this.name, other.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    
    public boolean addSong(Song song){
        if (songs.contains(song)){
            return false;
        }
        songs.add(song);
        return true;
    }
    
    public boolean removeSong(Song song){
        if (songs.contains(song)){
            songs.remove(song);
            return true;
        }
        return false;
    }
    
    public List<Song> getSortedPlaylistByPlayCount(){
        Comparator<Song> comp = (current, next)-> Integer.compare(current.getPlayCount(), next.getPlayCount());
        List<Song> sorted = new ArrayList<>(songs);
        Collections.sort(sorted, comp);
        return sorted;
    }
    
    public List<Song> getSortedPlaylistByName(){
        Comparator<Song> comp = (current, next)-> current.getName().compareTo(next.getName());
        List<Song> sorted = new ArrayList<>(songs);
        Collections.sort(sorted, comp);
        return sorted;
    }
    
    public List<Song> getShuffledPlayList(){
        List<Song> shuffled = new ArrayList<>(songs);
        Collections.shuffle(shuffled);
        return shuffled;
    }
    
}

class PlayListManager{
    Map<Integer, Song> songs = new HashMap<>();
    Map<String, Playlist> playlists = new HashMap<>();
    
    public PlayListManager(){
        songs.put(1, new Song(1, "Blinding Lights".toLowerCase()));
        songs.put(2, new Song(2, "Believer".toLowerCase()));
        songs.put(3, new Song(3, "Shape of You".toLowerCase()));
        songs.put(4, new Song(4, "Levitating".toLowerCase()));
        songs.put(5, new Song(5, "Starboy".toLowerCase()));
        songs.put(6, new Song(6, "Perfect".toLowerCase()));
        songs.put(7, new Song(7, "Closer".toLowerCase()));
        songs.put(8, new Song(8, "Someone You Loved".toLowerCase()));
    }
    
    public Map<Integer, Song> getAllSongs(){
        return songs;
    }
    
    public boolean songExists(int songID){
        return songs.keySet().contains(songID);
    }
    
    public boolean songExists(String songName){
        for (Song song : songs.values()){
            if (song.getName().equals(songName)){
                return true;
            }
        }
        return false;     
    }
    
    public List<String> getPlaylistNames(){
        List<String> names = new ArrayList<>(playlists.keySet());
        return names;        
    }
    
    public boolean addSongToPlayList(int songID, String playlistName){
        if (playlists.keySet().contains(playlistName)){
            boolean status = playlists.get(playlistName).addSong(songs.get(songID));
            return status;
        }
        return false;
    }
    
    public boolean createPlaylist(String playlistName){
        if (playlists.keySet().contains(playlistName)){
            return false;
        }
        playlists.put(playlistName, new Playlist(playlistName));
        return true;
    }
    
    public boolean playlistExists(String playlistName){
        return (playlists.keySet().contains(playlistName));
    }
    
    public List<Song> getShuffledPlaylist(String playlistName){
      return playlists.get(playlistName).getShuffledPlayList();        
    }    
    
    public void playSong(int songID){
        songs.get(songID).increasePlayCount();
    }
    
    public boolean markFavourite(int songID){
        return songs.get(songID).markFavourite();        
    }
    
    public boolean  unmarkFavourite(int songID){
        return songs.get(songID).unmarkFavourite();
    }
    
    public List<Song> sortPlaylistByPlayCount(String playlistName){
        return playlists.get(playlistName).getSortedPlaylistByPlayCount();
    }
    
    public List<Song> sortPlaylistByName(String playlistName){
        return playlists.get(playlistName).getSortedPlaylistByName();
    }
    
    public Song getSong(int songID){
        return songs.get(songID);
    }
    
}

class UserInterface{
    private Scanner sc = new Scanner(System.in);
    private PlayListManager playlistManager = new PlayListManager();
    private boolean exit = false;
    
    public void startProgram(){
        System.out.println("======= VF PLAYLIST MANAGER =======");
        System.out.println();
        System.out.println("==================");
        while(!exit){
            System.out.println();
            System.out.println("==================");
            System.out.println("[1] CREATE NEW PLAYLIST");
            System.out.println("[2] SHOW ALL SONGS");
            System.out.println("[3] ADD SONG TO PLAYLIST");
            System.out.println("[4] SHUFFLE A PLAYLIST");
            System.out.println("[5] SORT PLAYLIST");
            System.out.println("[6] PLAY SONG");
            System.out.println("[7] MARK FAVOURITE SONG");
            System.out.println("[8] UNFAVOURITE SONG");
            System.out.println("[9] SEARCH SONG");
            System.out.println("[10] EXIT");
            System.out.println();
            try{
                System.out.print("Your Choice(1-8) : ");
                int choice = sc.nextInt();
                switch (choice){
                    case 1:{
                        createNewPlaylist();
                        break;
                    }
                    case 2:{
                        showAllSongs();
                        break;
                    }
                    case 3:{
                        addSongToPlaylist();
                        break;
                    }
                    case 4:{
                        shufflePlaylist();
                        break;
                    }
                    case 5:{
                        sortPlaylist();
                        break;
                    }
                    case 6:{
                        playSong();
                        break;
                    }
                    case 7:{
                        markFavouriteSong();
                        break;
                    }           
                    case 8:{
                        unfavouriteSong();
                        break;
                    }         
                    case 9:{
                        searchSong();
                        break;
                    }
                    default:{
                        System.out.println("Thank You For Using..");
                        exit = true;
                    }
                }
            }
            catch(Exception e){
                System.out.println("Invalid Input!");
                System.out.println("Error Message : "+e);
                sc.nextLine();
            }
        }
    }
    
    public void searchSong(){
        System.out.println("[1] SEARCH SONG BY ID");
        System.out.println("[2] SEARCH SONG BY NAME");
        System.out.print("Your Choice : ");
        int choice2 = sc.nextInt();
        switch (choice2){
            case 1:{
                searchByID();
                break;
            }       
            case 2:{
                searchByName();
                break;
            }     
            default:{
                System.out.println("Invalid Choice!");
            }
        }
    }
    
    public void searchByName(){
        System.out.print("Enter Song Name : ");
        String name = sc.nextLine().toLowerCase();
        if (playlistManager.songExists(name)){
            List<Song> filtered = playlistManager.getAllSongs().values().stream().filter(song -> song.getName().equals(name)).collect(Collectors.toList());
            System.out.println("Song Found!");
            for (Song song : filtered){
                System.out.println("Name : "+song.getName());
                System.out.println("Favourite : "+song.getFavouriteStatus());
            System.out.println("Play Count : "+song.getPlayCount());
            }
        }
        else{
            System.out.println("Song Not Found!");
        }
    }
    
    public void searchByID(){
        System.out.print("Enter Song ID : ");
        sc.nextLine();
        int id = sc.nextInt();
        if(playlistManager.songExists(id)){
            Song song = playlistManager.getSong(id);
            System.out.println("Song Found!");
            System.out.println("Name : "+song.getName());
            System.out.println("Favourite : "+song.getFavouriteStatus());
            System.out.println("Play Count : "+song.getPlayCount());
        }
        else{
            System.out.println("Song Not Found!");
        }
    }
    
    public void unfavouriteSong(){
        List<Song> filtered = playlistManager.getAllSongs().values().stream().filter(song -> song.getFavouriteStatus() == true).collect(Collectors.toList());
        if (filtered.isEmpty()){
            System.out.println("No Songs Marked Favourite Yet!");
            return;
        }
        System.out.println("Favourite Songs :");
        for (Song song : filtered){
            System.out.println(song.getSongID()+". "+song.getName());
        }
        System.out.print("Enter Song ID :");
        int id = sc.nextInt();
        boolean contains = false;
        for(Song song : filtered){
            if (song.getSongID() == id){
                contains = true;
                break;
            }
        }
        if (contains){
            playlistManager.unmarkFavourite(id);
            System.out.println("Song was Unmarked As Favourite");
        }
        else{
            System.out.println("Invalid Song ID");
        }
    }
    
    public void markFavouriteSong(){        
        List<Song> filtered = playlistManager.getAllSongs().values().stream().filter(song -> song.getFavouriteStatus() == false).collect(Collectors.toList());
        System.out.println("Songs :-");
        for (Song song : filtered){
            System.out.println(song.getSongID()+". "+song.getName());
        }        
        System.out.println("==================");
        System.out.print("Enter ID Of Desired Song : ");
        int id = sc.nextInt();
        boolean contains = false;
        for (Song song : filtered){
            if (song.getSongID() == id){
                contains = true;
                break;
            }
        }
        if (contains){
            playlistManager.markFavourite(id);
            System.out.println("Song was Marked Favourite");
        }
        else{
            System.out.println("Invalid Song ID");
        }
    }
    
    public void playSong(){
        Map<Integer, Song> songs = playlistManager.getAllSongs();
        showAllSongs();
        System.out.println("==================");
        System.out.print("Enter Song ID : ");
        int id = sc.nextInt();
        if (id < 1 || id > songs.size()){
            System.out.println("Invalid Song ID");
            return;
        }
        playlistManager.playSong(id);
        System.out.println(songs.get(id).getName()+" was played");
    }
    public void sortPlaylist(){
        List<String> playlistNames = playlistManager.getPlaylistNames();        
        if (playlistNames.isEmpty()){
            System.out.println("No PlayLists Created Yet!");
            return;
        }
        System.out.println("[1] SORT PLAYLIST BY PLAY COUNT");
        System.out.println("[2] SORT BY PLAYLIST ALPHABETICAL ORDER");
        System.out.print("Enter Choice(1-2) : ");
        int choice2 = sc.nextInt();
        switch (choice2){
            case 1:{
                sortByPlayCount(playlistNames);
                break;
            }
            case 2 :{
                sortAlphabetically(playlistNames);
                break;
            }
            default:{
                System.out.println("Invalid Choice");
            }
        }
    }
    
    public void sortByPlayCount(List<String> playlistNames){
        int count = 1;
        System.out.println();
        System.out.println("PlayLists :-");
        System.out.println("==================");
        for (String name : playlistNames){
            System.out.println(count+". "+name);
            count++;
        }
        System.out.println();
        System.out.print("Enter No. Against Desired Playlist Name : " );
        int index = sc.nextInt()-1;
        if (index < 0 || index > playlistNames.size()-1){
            System.out.println("Invalid Playlist No. Entered");
            return;
        }
        List<Song> sorted = playlistManager.sortPlaylistByPlayCount(playlistNames.get(index));
        System.out.println("Sorted Songs :-");
        System.out.println("==================");
        for (Song song : sorted){
            System.out.println(song.getName()+song.getPlayCount());
        }
    }
    
    public void sortAlphabetically(List<String> playlistNames){
        int count = 1;
        System.out.println();
        System.out.println("PlayLists :-");
        System.out.println("==================");
        for (String name : playlistNames){
            System.out.println(count+". "+name);
            count++;
        }
        System.out.println();
        System.out.print("Enter No. Against Desired Playlist Name : " );
        int index = sc.nextInt()-1;
        if (index < 0 || index > playlistNames.size()-1){
            System.out.println("Invalid Playlist No. Entered");
            return;
        }
        List<Song> sorted = playlistManager.sortPlaylistByName(playlistNames.get(index));
        System.out.println("Sorted Songs :-");
        System.out.println("==================");
        for (Song song : sorted){
            System.out.println(song.getName());
        }
    }
    public void shufflePlaylist(){
        List<String> playlistNames = playlistManager.getPlaylistNames();        
        if (playlistNames.isEmpty()){
            System.out.println("No PlayLists Created Yet!");
            return;
        }
        
        int count = 1;
        System.out.println();
        System.out.println("PlayLists :-");
        System.out.println("==================");
        for (String name : playlistNames){
            System.out.println(count+". "+name);
            count++;
        }
        System.out.println();
        System.out.print("Enter No. Against Desired Playlist Name : " );
        int index = sc.nextInt()-1;
        if (index < 0 || index > playlistNames.size()-1){
            System.out.println("Invalid Playlist No. Entered");
            return;
        }
        List<Song> shuffled = playlistManager.getShuffledPlaylist(playlistNames.get(index));
        System.out.println("Shuffled PlayList : ");
        System.out.println("==================");
        for (Song song : shuffled){
            System.out.println(song.getName());
        }
    }
    
    public void addSongToPlaylist(){
        Map<Integer, Song> songs = playlistManager.getAllSongs();
        List<String> playlistNames = playlistManager.getPlaylistNames();        
        if (playlistNames.isEmpty()){
            System.out.println("No PlayLists Created Yet!");
            return;
        }
        showAllSongs();
        System.out.println("==================");
        System.out.print("Enter ID Of Song : ");
        int id = sc.nextInt();
        if (id < 1 || id > songs.size()){
            System.out.println("Invalid Song ID!");
            return;
        }
        
        int count = 1;
        System.out.println();
        System.out.println("PlayLists :-");
        System.out.println("==================");
        for (String name : playlistNames){
            System.out.println(count+". "+name);
            count++;
        }
        System.out.println();
        System.out.print("Enter No. Against Desired Playlist Name : " );
        int index = sc.nextInt()-1;
        if (index < 0 || index > playlistNames.size()-1){
            System.out.println("Invalid Playlist No. Entered");
            return;
        }
        boolean status = playlistManager.addSongToPlayList(id, playlistNames.get(index));
        if (status){
            System.out.println(songs.get(id).getName()+" added to "+playlistNames.get(index));
        }
        else{
            System.out.println("Song Laready Exists In Playlist!");
        }
    }
    
    public void showAllSongs(){
        Map<Integer, Song> songs = playlistManager.getAllSongs();
        System.out.println();
        System.out.println("==================");
        System.out.println("ID Song Name"); 

        System.out.println("==================");
        for (Integer key : songs.keySet()){
            System.out.println(key+"  "+songs.get(key).getName());
        }
    }
    
    public void createNewPlaylist(){
        System.out.println("==================");
        System.out.print("Enter Name Of New Playlist : ");
        sc.nextLine();
        String name = sc.nextLine().toLowerCase();
        boolean status = playlistManager.createPlaylist(name);
        if (status){
            System.out.println("Playlist ' "+name+" '"+" Created Successfully!");           
        }
        else{
            System.out.println("Playlist "+"'"+name+"'"+" Already Exists!");
        }
    }
}
public class Main {
	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		ui.startProgram();
	}
}