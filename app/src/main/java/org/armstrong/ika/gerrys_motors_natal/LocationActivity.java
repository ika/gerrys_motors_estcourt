package org.armstrong.ika.gerrys_motors_natal;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.google.gson.JsonObject;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.history.PNHistoryItemResult;
import com.pubnub.api.models.consumer.history.PNHistoryResult;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.api.models.consumer.pubsub.PNSignalResult;
import com.pubnub.api.models.consumer.pubsub.objects.PNMembershipResult;
import com.pubnub.api.models.consumer.pubsub.objects.PNSpaceResult;
import com.pubnub.api.models.consumer.pubsub.objects.PNUserResult;

import java.util.Arrays;
import java.util.Random;

public class LocationActivity extends AppCompatActivity {

    private SharedPreferences mSharedPrefs;
    private PubNub pubNub;
    private String userName;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-d1e1e402-dec8-11e9-b2f2-9a66d3fcadaa");
        pnConfiguration.setPublishKey("pub-c-3a8ed026-a211-4d68-82e6-838399af9159");

        pubNub.addListener(new SubscribeCallback() {

            @Override
            public void status(PubNub pubnub, PNStatus pnStatus) {
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult pnMessageResult) {
                // print basic info about newly received messages
                System.out.println("Message channel: " + pnMessageResult.getChannel());
                System.out.println("Message publisher: " + pnMessageResult.getPublisher());
                System.out.println("Message content: " + pnMessageResult.getMessage());
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult pnPresenceEventResult) {
                // print basic info about newly received presence events
                System.out.println("Presence channel: " + pnPresenceEventResult.getChannel());
                System.out.println("Presence event: " + pnPresenceEventResult.getEvent());
                System.out.println("Presence uuid: " + pnPresenceEventResult.getUuid());
            }

            @Override
            public void signal(PubNub pubnub, PNSignalResult pnSignalResult) {
            }

            @Override
            public void user(PubNub pubnub, PNUserResult pnUserResult) {
            }

            @Override
            public void space(PubNub pubnub, PNSpaceResult pnSpaceResult) {
            }

            @Override
            public void membership(PubNub pubnub, PNMembershipResult pnMembershipResult) {
            }
        });

        pubNub.subscribe()
                .channels(Arrays.asList("pubnub_onboarding_channel"))
                .withPresence() // to receive presence events
                .execute();

        JsonObject message = new JsonObject();
        message.addProperty("sender", pnConfiguration.getUuid());
        message.addProperty("text", "Hello From Java SDK");

        pubNub.publish()
                .message(message)
                .channel("pubnub_onboarding_channel")
                .async(new PNCallback<PNPublishResult>() {
                    @Override
                    public void onResponse(PNPublishResult result, PNStatus status) {
                        if (!status.isError()) {
                            System.out.println("Message timetoken: " + result.getTimetoken());
                        } else {
                            status.getErrorData().getThrowable().printStackTrace();
                        }
                    }
                });

        pubNub.history()
                .channel("pubnub_onboarding_channel")
                .count(10)
                .includeTimetoken(true)
                .async(new PNCallback<PNHistoryResult>() {
                    @Override
                    public void onResponse(PNHistoryResult result, PNStatus status) {
                        if (!status.isError()) {
                            for (PNHistoryItemResult historyItem : result.getMessages()) {
                                System.out.println("Message content: " + historyItem.getEntry());
                            }
                            System.out.println("Start timetoken: " + result.getStartTimetoken());
                            System.out.println("End timetoken: " + result.getEndTimetoken());
                        } else {
                            status.getErrorData().getThrowable().printStackTrace();
                        }
                    }
                });

    }
}
