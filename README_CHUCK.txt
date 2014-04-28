Project used to require jdk 1.7, but I got rid of 
List<Todo>  ... = new ArrayList<>();   so that we actually had to fill in the diamond and make it new ArrayList<KTodo>();

I did this because Vmware vFabric tcServer Developer Edition 2.9 complained about starting a server with jdk 1.7 facet.

You can go back to 1.7 by searching pom.xml for 1.6 and making it 1.7, so long as the 1.6 referers to the JDK.


!!Looks like the facet problem still exists when I try to create a vmWare tcServer and then put rest-unit into it.
So for now I'm moving back to jdk 1.7!!  
RESULT: No I'm not!
Turns out you can fix that by right clicking on the top onde of your project, searching for Project Facets in the "type filter test', assuming you don't see it immediately, and changing the Java setting in the right hand pane from 1.7 to 1.6.  If I move to a different JDK, I think it would be jdk 1.8.
