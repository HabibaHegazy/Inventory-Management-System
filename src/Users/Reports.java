
package Users;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.Date;

public class Reports {
    
    private String a3lan = "no notifcation";
    
    public void announcementReport(String announcement, Date ReportDate, int reportNum) {   
        // save into arrayList
        a3lan = announcement;
    }
    
    public void getVoiceOut() {   
        String VoiceName = "kevin16";
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VoiceName);
        voice.allocate();
        try {
            voice.speak(a3lan);
        } catch (Exception e) {
            System.out.println("Error Massage"); // dialog needed
        }
    }
    
    public void customerslssues(String x,Date date ,int y,String z)
    {}
    public void bestSoldProducts(String x,String y,int z,int m)
    {}
    
    //** mohem da ......
    public void productQualfcations(String x,String y)
    {}
    
    
}
