package rem.proxie.message_service;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class Message implements Parcelable{

    public static final String BROADCAST_MESSAGE = "BROADCAST";
    public static final String ANONYMOUS = "Anonymous";
    public static final int TRUE = 1;
    public static final int FALSE = 0;

    private String text;
    private String sender;
    private String target;
    private double sendersLatitude;
    private double sendersLongitude;
    private int numHops;
    private long expirationTime;
    private int createdByUser;

    /* Create a new message */
    public Message(String text, String sender, String target, LatLng sendersLocation,
                   int numHops, long expirationTime, int createdByUser) {

        this.text = text;
        this.sender = sender;
        this.target = target;
        this.sendersLatitude = sendersLocation.latitude;
        this.sendersLongitude = sendersLocation.longitude;
        this.numHops = numHops;
        this.expirationTime = expirationTime;
        this.createdByUser = createdByUser;
    }

    /* Getter methods */
    public String getText()             { return text; }
    public String getSender()           { return sender; }
    public String getTarget()           { return target; }
    public LatLng getSendersLocation()  { return new LatLng(sendersLatitude, sendersLongitude); }
    public int getNumHops()             { return numHops; }
    public long getExpirationTime()     { return expirationTime; }
    public int getCreatedByUser()       { return createdByUser; }

    /* Setter methods */
    public void decrementNumHops()      { numHops = (numHops > 0)? numHops -1 : numHops; }

    /* Methods in Parcelable Interface */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(sender);
        dest.writeString(target);
        dest.writeDouble(sendersLatitude);
        dest.writeDouble(sendersLongitude);
        dest.writeInt(numHops);
        dest.writeLong(expirationTime);
        dest.writeInt(createdByUser);
    }

    // Used to regenerate your object.
    // All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    // Constructor that takes a Parcel populates the fields
    private Message(Parcel in) {
        text = in.readString();
        sender = in.readString();
        target = in.readString();
        sendersLatitude = in.readDouble();
        sendersLongitude = in.readDouble();
        numHops = in.readInt();
        expirationTime = in.readLong();
        createdByUser = in.readInt();
    }
}
