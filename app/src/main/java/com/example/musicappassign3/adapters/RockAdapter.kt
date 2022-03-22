package com.example.musicappassign3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicappassign3.R
import com.example.musicappassign3.model.RockItem
import com.example.musicappassign3.rest.MusicAPI
import com.squareup.picasso.Picasso

class RockAdapter(
    private val rockCollectionItem: MutableList<RockItem> = mutableListOf(),
    private val onTabClicked: (RockItem) -> Unit

): RecyclerView.Adapter<RockMusicViewHolder>(){

    fun updateRockList(newRockTrack: RockItem){
        rockCollectionItem.clear()
        rockCollectionItem.addAll(newRockTrack)
        notifyDataSetChanged()


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RockMusicViewHolder {
        val rockView = LayoutInflater.from(parent.context).inflate(R.layout.track_item, parent, false)
        return RockMusicViewHolder(rockView)
    }

    override fun onBindViewHolder(holder: RockMusicViewHolder, position: Int) {
        val aRockTrack = rockCollectionItem[position]
        holder.bind(aRockTrack)
    }

    override fun getItemCount(): Int = rockCollectionItem.size
}

class RockMusicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private val rockArtist: TextView = itemView.findViewById(R.id.artist_name)
    private val rockSong: TextView = itemView.findViewById(R.id.song_name)
    private val rockTrackPrice: TextView = itemView.findViewById(R.id.track_price)
    private val rockAlbumCover: ImageView = itemView.findViewById(R.id.artist_photo)

    fun bind (aRockTrack: RockItem){
        rockArtist.text = aRockTrack.artistName
        rockSong.text = aRockTrack.collectionName
        rockTrackPrice.text = aRockTrack.trackPrice.toString()

        Picasso.get()
            .load(MusicAPI.ROCK_MUSIC_URL + aRockTrack.artworkUrl60)
            .placeholder(R.drawable.ic_baseline_front_hand_24)
            .error(R.drawable.ic_baseline_error_24)
            .resize(250, 250)
            .into(rockAlbumCover)

    }

}