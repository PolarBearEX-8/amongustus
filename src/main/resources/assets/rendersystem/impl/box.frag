#version 120

uniform float power;
uniform int isFirstPass;

uniform sampler2D tex;
uniform sampler2D mask;
uniform vec2 direction;
uniform vec2 resolution;

void main() {
    vec2 uv = gl_FragCoord.xy / resolution;

    float maskAlpha = texture2D(mask, uv).a;
    if (maskAlpha <= 0.0) {
        if (isFirstPass == 1) {
            gl_FragColor = texture2D(tex, uv);
        } else {
            discard;
        }
        return;
    }

    vec3 color = vec3(0.0);

    float pixelSkip = float(int(power)) / 2.0;

    int iterations = 0;
    for (float x = -pixelSkip; x <= pixelSkip; x += 1.0) {
        color += texture2D(tex, (gl_FragCoord.xy + direction * x) / resolution).rgb;
        iterations++;
    }

    gl_FragColor = vec4(color / float(iterations), 1.0);
}
