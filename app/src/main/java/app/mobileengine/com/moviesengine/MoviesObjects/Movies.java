package app.mobileengine.com.moviesengine.MoviesObjects;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by praveen on 4/2/2016.
 */
public class Movies implements Parcelable{
    private String mPosterPath;
    private String mBackDropPath;
    private String mOverview;
    private String mReleaseDate;
    private String mMovieId;
    private String mTitle;
    private String mVideo;
    private String mVoteCount;
    private String mVoteAverage;
    private String mIsFavourite = "false";
    private String mPopularity;
    private String mGenre;
    private String mRunTime;
    private String mLanguage;
    private String mCast;
    private String mCrew;



    private Context mContext;


    public Movies(String mPosterPath,String mBackDropPath, String mOverview, String mReleaseDate, String mMovieId, String mTitle, String mVideo,
                  String mVoteCount, String mVoteAverage, String mIsFavourite,String mPopularity, Context mContext) {
        this.mPosterPath = mPosterPath;
        this.mBackDropPath = mBackDropPath;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
        this.mMovieId = mMovieId;
        this.mTitle = mTitle;
        this.mVideo = mVideo;
        this.mVoteCount = mVoteCount;
        this.mVoteAverage = mVoteAverage;
        this.mIsFavourite = mIsFavourite;
        this.mPopularity = mPopularity;
        this.mContext = mContext;

    }

    protected Movies(Parcel in) {
        mPosterPath = in.readString();
        mBackDropPath = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
        mMovieId = in.readString();
        mTitle = in.readString();
        mVideo = in.readString();
        mVoteCount = in.readString();
        mVoteAverage = in.readString();
        mIsFavourite = in.readString();
        mGenre = in.readString();
        mRunTime = in.readString();
        mLanguage = in.readString();
        mCast = in.readString();
        mCrew = in.readString();

    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    public String getmPopularity() {
        return mPopularity;
    }

    public void setmPopularity(String mPopularity) {
        this.mPopularity = mPopularity;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPosterPath);
        dest.writeString(mBackDropPath);
        dest.writeString(mOverview);
        dest.writeString(mReleaseDate);
        dest.writeString(mMovieId);
        dest.writeString(mTitle);
        dest.writeString(mVideo);
        dest.writeString(mVoteCount);
        dest.writeString(mVoteAverage);
        dest.writeString(mIsFavourite);
        dest.writeString(mGenre);
        dest.writeString(mRunTime);
        dest.writeString(mLanguage);
        dest.writeString(mCast);
        dest.writeString(mCrew);

    }

    public String getmCrew() {
        return mCrew;
    }

    public void setmCrew(String mCrew) {
        this.mCrew = mCrew;
    }

    public String getmCast() {
        return mCast;
    }

    public void setmCast(String mCast) {
        this.mCast = mCast;
    }

    public String getmBackDropPath() {
        return mBackDropPath;
    }

    public void setmBackDropPath(String mBackDropPath) {
        this.mBackDropPath = mBackDropPath;
    }

    public String getmLanguage() {
        return mLanguage;
    }

    public void setmLanguage(String mLanguage) {
        this.mLanguage = mLanguage;
    }

    public String getmRunTime() {
        return mRunTime;
    }

    public void setmRunTime(String mRunTime) {
        this.mRunTime = mRunTime;
    }

    public String getmGenre() {
        return mGenre;
    }

    public void setmGenre(String mGenre) {
        this.mGenre = mGenre;
    }

    public String getmIsFavourite() {
        return mIsFavourite;
    }

    public void setmIsFavourite(String mIsFavourite) {
        this.mIsFavourite = mIsFavourite;
    }

    public String getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(String mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getmMovieId() {
        return mMovieId;
    }

    public void setmMovieId(String mMovieId) {
        this.mMovieId = mMovieId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmVideo() {
        return mVideo;
    }

    public void setmVideo(String mVideo) {
        this.mVideo = mVideo;
    }

    public String getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(String mVoteCount) {
        this.mVoteCount = mVoteCount;
    }


}
