package com.example.socialview

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import butterknife.BindView
import com.hendraanggrian.socialview.SocialTextWatcher
import com.hendraanggrian.socialview.commons.Hashtag
import com.hendraanggrian.socialview.commons.HashtagAdapter
import com.hendraanggrian.socialview.commons.Mention
import com.hendraanggrian.socialview.commons.MentionAdapter
import com.hendraanggrian.widget.SocialAutoCompleteTextView

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
class Example3Activity : BaseActivity(), SocialTextWatcher {

    @BindView(R.id.toolbar_example3) lateinit var toolbar: Toolbar
    @BindView(R.id.socialautocompletetextview_example3) lateinit var socialAutoCompleteTextView: SocialAutoCompleteTextView<Hashtag, Mention>

    override val contentView: Int
        get() = R.layout.activity_example3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        socialAutoCompleteTextView.hashtagAdapter = HashtagAdapter(this)
        socialAutoCompleteTextView.mentionAdapter = MentionAdapter(this)
        socialAutoCompleteTextView.setHashtagTextChangedListener(this)
        socialAutoCompleteTextView.setMentionTextChangedListener(this)

        val hashtag1 = Hashtag("follow")
        val hashtag2 = Hashtag("followme", 1000)
        val hashtag3 = Hashtag("followmeorillkillyou", 500)
        socialAutoCompleteTextView.hashtagAdapter.addAll(hashtag1, hashtag2, hashtag3)

        val mention1 = Mention("dirtyhobo")
        val mention2 = Mention("hobo", "Regular Hobo", R.mipmap.ic_launcher)
        val mention3 = Mention("hendraanggrian", "Hendra Anggrian", "https://avatars0.githubusercontent.com/u/11507430?v=3&s=460")
        socialAutoCompleteTextView.mentionAdapter.addAll(mention1, mention2, mention3)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onSocialTextChanged(v: TextView, s: CharSequence) {
        Log.d("editing", s.toString())
    }
}