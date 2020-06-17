/*
 * Copyright (c) 2018, Nordic Semiconductor
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package no.nordicsemi.android.ble.response;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import no.nordicsemi.android.ble.callback.DataSentCallback;
import no.nordicsemi.android.ble.data.Data;

/**
 * Generic write response class that returns the target device and the data that were sent.
 * Overriding class must call super on {@link #onDataSent(BluetoothDevice, Data)} in
 * order to make getters work properly.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class WriteResponse implements DataSentCallback, Parcelable {
	private BluetoothDevice device;
	private Data data;

	@Override
	public void onDataSent(@NonNull final BluetoothDevice device, @NonNull final Data data) {
		this.device = device;
		this.data = data;
	}

	@Nullable
	public BluetoothDevice getBluetoothDevice() {
		return device;
	}

	@Nullable
	public Data getRawData() {
		return data;
	}

	// Parcelable
	protected WriteResponse(final Parcel in) {
		device = in.readParcelable(BluetoothDevice.class.getClassLoader());
		data = in.readParcelable(Data.class.getClassLoader());
	}

	@Override
	public void writeToParcel(final Parcel dest, final int flags) {
		dest.writeParcelable(device, flags);
		dest.writeParcelable(data, flags);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<WriteResponse> CREATOR = new Creator<WriteResponse>() {
		@Override
		public WriteResponse createFromParcel(final Parcel in) {
			return new WriteResponse(in);
		}

		@Override
		public WriteResponse[] newArray(final int size) {
			return new WriteResponse[size];
		}
	};
}
