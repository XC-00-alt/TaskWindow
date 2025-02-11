package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class was planned to be a view that snapshots what is on the screen,
 * hence the name: DateArchive.
 * But it doesn't seem intuitive, so this may be turned into a view that can be customized,
 * where the user can assign some notes to be shown in the view.
 * There will be 2 views by default:
 * one that shows incomplete notes, the other that shows completed note.
 * This may be implemented later, but the project is rather complete by now (2025y/2M/8d).
 */
public class ArchiveView {
    private String name;
    private List<Integer> noteIdList=new ArrayList<>();
}
//public class DateArchive {
//    private Date date;
//    private List<Integer> noteIdList=new ArrayList<>();
//}
