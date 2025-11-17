package commonUtils;


public class CommonData {
    private static ThreadLocal<Integer>threadBookingId=new ThreadLocal<Integer>();
    private static ThreadLocal<Object>bookingPojoThreadLocal=ThreadLocal.withInitial(() -> {return new Object();});
    private CommonData(){}
    public static void setBookingId(int bookingId){
        threadBookingId.set(bookingId);
    }
    public static void getBookingId(){
        threadBookingId.get();
    }

    public static void setUsers(Object users){
        bookingPojoThreadLocal.set(users);
    }

    public static Object getUsers(){
        return bookingPojoThreadLocal.get();
    }

    public static void clearCurrentUser() {
        bookingPojoThreadLocal.remove(); // Essential for preventing memory leaks
    }

    public static void clearCurrentBookingIds() {
        threadBookingId.remove(); // Essential for preventing memory leaks
    }

}
