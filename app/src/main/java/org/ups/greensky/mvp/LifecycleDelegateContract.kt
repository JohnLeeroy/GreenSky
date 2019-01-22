package org.ups.greensky.mvp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

interface LifecycleDelegateContract {

  fun onCreate(bundle: Bundle?)
  fun onDestroy()
  fun onPause()
  fun onResume()

  fun onSaveInstanceState(outState: Bundle)
  fun onRestoreInstanceState(savedInstanceState: Bundle)

  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

  fun onViewAttached()
  fun onViewDetached()
  fun save(outBundle: Bundle)
  fun load(bundle: Bundle)
}