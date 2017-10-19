package br.tiago.geocharts.controller;

import java.io.File;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Media;
import facebook4j.PhotoUpdate;
import facebook4j.TagUpdate;
import facebook4j.auth.AccessToken;

public class FBController {
	private Facebook fb;
	public FBController (String appId, String appSecret, String token) {
		fb = new FacebookFactory().getInstance();
    	fb.setOAuthAppId(appId, appSecret);
    	fb.setOAuthPermissions("public_profile, user_birthday, user_religion_politics, user_relationships, user_relationship_details, user_hometown, user_location, user_likes, user_activities, user_interests, user_education_history, user_work_history, user_website, user_groups, user_events, user_photos, user_videos, user_friends, user_about_me, user_status, user_games_activity, user_tagged_places, user_actions.books, user_actions.music, user_actions.video, user_actions.news");
        fb.setOAuthAccessToken(new AccessToken(token, null));
    }

    public void publish (String msg, File photo, String usr) {
        Media photoM = new Media(photo);
        PhotoUpdate post = new PhotoUpdate(photoM)
            .message(msg);

        try {
            String photoId = fb.postPhoto(post);
            TagUpdate tags = new TagUpdate()
                .to(usr);
            fb.updateTagOnPhoto(photoId, tags);
        } catch (FacebookException e) {
            e.printStackTrace();
        }
    }
}
