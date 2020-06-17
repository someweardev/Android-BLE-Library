package no.nordicsemi.android.ble;

import android.support.annotation.NonNull;

abstract class RequestHandler implements CallbackHandler {
	/**
	 * Enqueues the given request at the end of the the init or task queue, depending
	 * on whether the initialization is in progress, or not.
	 *
	 * @param request the request to be added.
	 */
	abstract void enqueue(@NonNull final Request request);

	/**
	 * Enqueues the given request at the front of the the init or task queue, depending
	 * on whether the initialization is in progress, or not.
	 *
	 * @param request the request to be added.
	 */
	abstract void enqueueFirst(@NonNull final Request request);

	/**
	 * Removes all enqueued requests from the queue.
	 */
	abstract void cancelQueue();

	/**
	 * Method called when the request timed out.
	 *
	 * @param request the request that timed out.
	 */
	abstract void onRequestTimeout(@NonNull final TimeoutableRequest request);
}
