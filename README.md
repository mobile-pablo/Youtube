# <center><img src= "https://freepngimg.com/download/youtube/77699-classic-icons-youtube-computer-logo-icon.png" style="width:30px; height:30px" /> Youtube </center>

## <center>TV - oriented Clone of Youtube with their API </center>

## 🪚 Status

    App has basic functions like trending video browsing and playing videos but there is one issue.
    Youtube API doesnt support any views only iframe which is not so much supported by Android TV.
    I can like open video and play it but not so much add my own features like comment or like.
    That cuts many of future features that I would implement.
    Only few external like share and title with redirrection to Youtube App.
    I have decided to cut external app opening but I wont  be able to remove Share button.

    Soon App will have Search Option with history saving.

[Iframe issue](https://stackoverflow.com/questions/9640265/problems-giving-focus-to-an-iframe-on-smart-tv)
I was regarding to

## 🎯​ Test Coverage ████████░░ 80%

    Most important files are covered currently (1.0.0 status)

## 🙊​ Google Play - Status

    App new releases only will be internal for Youtube Privacy Law.
    Non less it still uses CD - Deploy workflow only not for production

## 📱 How to run

1. Install Android Studio according
   to [tutorial](https://developer.android.com/studio?gclid=CjwKCAjwnOipBhBQEiwACyGLukOqCPF7rjbRbw2zo-hldBEOSCVk0P0B1bFUCJjxdKyi6zGc3daUzhoCwSkQAvD_BwE&gclsrc=aw.ds)
2. Download necessary SDK tools
3. Configure Android emulator or connect external device
4. Clone repository from button '<> Code'
5. Click Run button : )

## ⚙️ Config

Config files based on Kotlin DSL. <br/>
Dependencies in TOML file

## 🤔​ Planning

Jira
at [Youtube Jira](https://mobile-pablo.atlassian.net/jira/software/c/projects/YTV/boards/5) <br/>
Qase at [Youtube Qase](https://app.qase.io/project/YTC) <br/>

## 🛠️​ Tech stack

* <h4> Multi-module </h4>
* <h4> Retrofit </h4>
* <h4> OkHttp </h4>
* <h4> Hilt + Dagger </h4>
* <h4> Compose </h4>
* <h4> Coroutines </h4>
* <h4> Compose Navigation </h4>
* <h4> Paging3 </h4>
* <h4> Coil </h4>
* <h4> KtLint </h4>
* <h4> Leanback </h4>
* <h4> Youtube Android Player (iFrame) </h4>
* <h4> Permissions - Accompanist </h4>
* <h4> Tests</h4>

    * <h5> Compose testing </h5>
    * <h5> Mockk</h5>
    * <h5> Turbine - flow testing </h5>
    * <h5> Mock Web Server </h5>
    * <h5> Truth </h5>
* <h4> Firebase App Distribution </h4>
* <h4> Crashlytics </h4>
* <h4> Github Actions </h4>
* <h4> Bitrise CI/CD </h4>

    * <b>CI</b>  <i>(primary workflow)</i>  with integration, widget and unit tests on <b>
      develop</b> branch

    * <b>CD</b>  <i>(staging workflow)</i> deploy to Firebase App Distribution on <b>
      x.y.z/staging</b> branch

    * <b>CD</b>  <i>(deploy workflow)</i>  with deploy to Google Play on <b>master</b> branch
* <h4>Github Actions </h4>

    * Delete Branch
    * Lint
    * Mark stale issues

## To be added soon in (1.0.1)

* Add Kover plugin

## 📸 Screenshots

* <h3><b>App</b> with 60-30-10 design</h3>

<table border="1">
<thead>
  <tr>
    <th>Light mode</th>
    <th>Dark mode</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td><img  src="readme_assets/loading_screen_light.png" /></td>
    <td><img  src="readme_assets/loading_screen_dark.png" /></td>
  </tr>
  <tr>
    <td><img  src="readme_assets/loaded_popular_screen_light.png" /></td>
    <td><img  src="readme_assets/loaded_popular_screen_dark.png" /></td>
  </tr>
<tr>
    <td><img  src="readme_assets/player_screen_light.png"  /></td>
    <td><img  src="readme_assets/player_screen_dark.png" /></td>
</tr>
</tbody>
</table>

* <h3><b>CI/CD</b></h3>

* <b>CI</b> <i>(primary)</i>
   <div> <img  src="readme_assets/bitrise_ci_primary.png" /> </div>

* <b>CD</b> <i>(staging)</i>

  <div> <img  src="readme_assets/bitrise_cd_staging.png" /> </div>

* <b>CD</b> <i>(deploy)</i>

  <div> <img  src="readme_assets/bitrise_cd_deploy.png" /> </div>

* <h3><b>Config</b></h3>

    * <b> Firebase App Distribution </b>
        <div> <img  src="readme_assets/firebase_app_distribution.png" /> </div>
    <br/>

    * <b> Jira</b>
        <div> <img  src="readme_assets/jira_kanban.png" /> </div>
    <br/>

    * <b> Qase (to be improved)</b>
        <div> <img  src="readme_assets/qase_suites.png" /> </div>
    <br/>

    * <b> Crashlytics </b>
        <div> <img  src="readme_assets/crashlytics.png" /> </div>

## 🫧​ Design

<center>
    I implemented and used 60 - 30 - 10 rule for this design
    <br/><br/>
    <img  width = "50%" src="https://cdn.dribbble.com/users/2248264/screenshots/4552223/media/5ee3ff95aa8d67a446556a3ff15e8001.jpg?resize=800x600&vertical=center"/> <br/>
  Implementation based on [Youtube TV Design](https://dribbble.com/shots/4552223-YouTube-TV-App-Redsign)
</center>