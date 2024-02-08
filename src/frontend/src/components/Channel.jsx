import React, { useEffect, useState } from "react";
import "../css/Channels.css";
import {
  channelList as mock_channel_list,
  memberList as mock_member_list,
} from "../config/mock_data";
import CurrentStore from "../store/CurrentStore";
import CommunityStore from "../store/CommunityStore";
import { useNavigate } from "react-router-dom";
import ChannelButton from "./ChannelButton";
import VoiceChannelButton from "./VoiceChannelButton";

const Channel = () => {
  const navigate = useNavigate();
  const {
    CURRENT_VIEW_GUILD,

    CURRENT_VIEW_GUILD_NAME,
  } = CurrentStore();
  const { CHANNEL_LIST, setChannelList } = CommunityStore();
  const [members, setMembers] = useState([]);

  useEffect(() => {
    // ****************** 길드 채널 최초 접속 시 ******************
    if (CURRENT_VIEW_GUILD) {
      // axios를 통한 채널 리스트, memberList 요청
      const channelResponse = mock_channel_list.resultData.filter((element) => {
        return element.guildId === CURRENT_VIEW_GUILD;
      });
      const memberResponse = mock_member_list.resultData; //

      // response를 세팅
      setChannelList(channelResponse);
      setMembers(memberResponse);

      // 첫 번째 채팅 채널로 이동
      const channel = channelResponse?.find(
        (element) => element.type === "TEXT"
      );
      if (channel) {
        navigate(`/channels/${CURRENT_VIEW_GUILD}/${channel?.channelReadId}`);
      }
    }
    return () => {};
  }, [CURRENT_VIEW_GUILD]);

  return (
    <div className="channel-container">
      <div className="channel-title-container">
        <p className="channel-title">{CURRENT_VIEW_GUILD_NAME}</p>
      </div>

      <div className="channel-list-container">
        {CHANNEL_LIST ? (
          CHANNEL_LIST?.map((channel) =>
            channel.type !== "VOICE" ? (
              <ChannelButton key={channel.channelReadId} channel={channel} />
            ) : (
              <VoiceChannelButton
                key={channel.channelReadId}
                channel={channel}
              />
            )
          )
        ) : (
          <></>
        )}
      </div>
    </div>
  );
};

export default Channel;
