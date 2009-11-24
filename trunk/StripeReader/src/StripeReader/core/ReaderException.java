package StripeReader.core;

public final class ReaderException extends Exception {

    // TODO: Currently we throw up to 400 ReaderExceptions while scanning a single 240x240 image before
    // rejecting it. This involves a lot of overhead and memory allocation, and affects both performance
    // and latency on continuous scan clients. In the future, we should change all the decoders not to
    // throw exceptions for routine events, like not finding a barcode on a given row. Instead, we
    // should return error codes back to the callers, and simply delete this class. In the mean time, I
    // have altered this class to be as lightweight as possible, by ignoring the exception string, and
    // by disabling the generation of stack traces, which is especially time consuming. These are just
    // temporary measures, pending the big cleanup.
    private static final ReaderException instance = new ReaderException();

    private ReaderException() {
        // do nothing
    }

    public static ReaderException getInstance() {
        return instance;
    }

    // Prevent stack traces from being taken
    // srowen says: huh, my IDE is saying this is not an override. native methods can't be overridden?
    // This, at least, does not hurt. Because we use a singleton pattern here, it doesn't matter anyhow.
    public Throwable fillInStackTrace() {
        return null;
    }
}
