# <center><img src= "https://freepngimg.com/download/youtube/77699-classic-icons-youtube-computer-logo-icon.png" style="width:30px; height:30px" /> Youtube </center>
 ## <center>TV - oriented Clone of Youtube with their API </center>

## 🪚 Status 
    App has basic functions like trending video browsing and playing videos but there is one issue.
    Youtube API doesnt support any views only iframe which is not so much supported by Android TV.
    I can like open video and play it but not so much add my own features like comment or like.
    That cuts many of future features that I would implement.
    Only few external like share and title with redirrection to Youtube App.

    Soon App will have Search Option with history saving.
    
## 📱 How to run
1. Install Android Studio according to [tutorial](https://developer.android.com/studio?gclid=CjwKCAjwnOipBhBQEiwACyGLukOqCPF7rjbRbw2zo-hldBEOSCVk0P0B1bFUCJjxdKyi6zGc3daUzhoCwSkQAvD_BwE&gclsrc=aw.ds)
2. Download necessary SDK tools
3. Configure Android emulator or connect external device
4. Clone repository from button '<> Code'
5. Click Run button : )

## ⚙️ Config
Config files based on Kotlin DSL. <br/>
Dependencies in TOML file

## 🤔​ Planning
Jira at [Youtube Jira](https://mobile-pablo.atlassian.net/jira/software/c/projects/YTV/boards/5) <br/>
Qase at [Youtube Qase](https://app.qase.io/project/YTC) <br/>

## 🛠️​ Tech stack
* <h4> Multi-module </h4>
* <h4> Retrofit </h4>
* <h4> OkHttp </h4>
* <h4> Hilt + Dagger </h4>
* <h4> Compose </h4>
* <h4> Coroutines </h4>
* <h4> Compose Navigation </h4>
* <h4> Coil </h4>
* <h4> ExoPlayer </h4>
* <h4> KtLint </h4>
* <h4> Leanback </h4>
* <h4> Permissions - Accompanist </h4>
* <h4> Tests </h4>
  
  * <h5> Mockito </h5>
  * <h5> Mockk</h5>
  * <h5>Espresso </h5>
  * <h5> Truth </h5>
* <h4> Firebase App Distribution </h4>
* <h4> Github Actions </h4>
* <h4> Bitrise CI/CD </h4>

    * <b>CI</b>  <i>(primary workflow)</i>  with integration, widget and unit tests on <b>develop</b> branch

    * <b>CD</b>  <i>(staging workflow)</i> deploy to Firebase App Distribution on <b>x.y.z/staging</b> branch

    * <b>CD</b>  <i>(deploy workflow)</i>  with deploy to Google Play on <b>master</b> branch
* <h4>Github Actions </h4>

    * Delete Branch
    * Lint
    * Mark stale issues