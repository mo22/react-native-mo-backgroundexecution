package de.mxs.reactnativemobackgroundexecution;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.M)
public class BackgroundJobService extends JobService {

  public static void schedule(Context context) {
    Log.i("XXX", "BackgroundJobService.schedule");
    context.getSystemService(JobScheduler.class).schedule(
      new JobInfo.Builder(0, new ComponentName(context, BackgroundJobService.class))
        .setMinimumLatency(300 * 1000)
        .setOverrideDeadline(600 * 1000)
        .build()
    );
  }

  @Override
  public boolean onStartJob(JobParameters params) {
    Log.i("XXX", "BackgroundJobService.onStartJob");
    schedule(getApplicationContext());
    return true;
  }

  @Override
  public boolean onStopJob(JobParameters params) {
    Log.i("XXX", "BackgroundJobService.onStopJob");
    return true;
  }
}
